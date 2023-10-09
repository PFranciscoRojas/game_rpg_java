package database;
import enums.Elements;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionInventoryDB {
    private Connection connection = null;

    public ConnectionInventoryDB() {

        ConfigurationDB Setting = new ConfigurationDB();
        String url = Setting.getUrl();
        String user = Setting.getUser();
        String password = Setting.getPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Cargando Inventario");

        } catch (SQLException ex) {
            // Manejar la excepción de conexión y proporcionar un mensaje descriptivo
            System.out.println("Error al conectar a la base de datos: ");
        }
    }
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {

            throw new RuntimeException("Error al cerrar tabla", ex);
        }
    }
    public  List<Elements> listElements() {
        String consulta = "SELECT iny.*, ste.* FROM inventory iny INNER JOIN store ste ON iny.id_store = ste.id";
        List<Elements> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Elements object = new Elements();
                    object.setName(resultSet.getString("ste.name"));
                    object.setDescription(resultSet.getString("ste.description"));
                    object.setScore(resultSet.getInt("ste.score"));
                    object.setGold(resultSet.getInt("ste.gold"));
                    object.setCategory(resultSet.getString("ste.category"));
                    list.add(object);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos", ex);
        }
        return list;
    }


}
