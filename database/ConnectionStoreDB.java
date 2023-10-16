package database;
import enums.Elements;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionStoreDB {
    private Connection connection = null;
    public ConnectionStoreDB() {

        ConfigurationDB Setting = new ConfigurationDB();
        String url = Setting.getUrl();
        String user = Setting.getUser();
        String password = Setting.getPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("conection Exitosa");

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
    public List<Elements> listElements(int Type) {
        String consulta = "SELECT * FROM store WHERE category_id = ?";
        List<Elements> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setInt(1, Type);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Elements object = new Elements();
                    object.setId(resultSet.getInt("id"));
                    object.setName(resultSet.getString("name_item"));
                    object.setType(resultSet.getString("category"));
                    object.setDescription(resultSet.getString("description_item"));
                    object.setScore(resultSet.getInt("score"));
                    object.setGold(resultSet.getInt("gold"));
                    object.setCategory(resultSet.getInt("category_id"));
                    list.add(object);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los Elementos", ex);
        }

        return list;
    }
}
