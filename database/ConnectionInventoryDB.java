package database;
import enums.Elements;
import src.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionInventoryDB {
    private Connection connection = null;
    String query;
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
        query = "SELECT iny.*, str.* FROM inventory iny INNER JOIN store str ON iny.store_id = str.id";
        List<Elements> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Elements object = new Elements();
                    object.setId(resultSet.getInt("str.id"));
                    object.setName(resultSet.getString("str.name_item"));
                    object.setDescription(resultSet.getString("str.description_item"));
                    object.setScore(resultSet.getInt("str.score"));
                    object.setGold(resultSet.getInt("str.gold"));
                    object.setCategory(resultSet.getInt("str.category_id"));
                    list.add(object);
                }

            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos", ex);
        }

        return list;

    }
    public void insertElement(int id, int idCharacter){
        try {
            connection.setAutoCommit(false);
            query = "INSERT INTO inventory (store_id,personage_id) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.setInt(2, idCharacter);
                int rowsInserted = statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error al realizar rollback de la transacción", rollbackEx);
            }
            throw new RuntimeException("Error al insertar el elemento en la tabla", ex);
        }
    }
    public void DeleteElement(int id){
        try {
            connection.setAutoCommit(false);
            query = "DELETE FROM inventory WHERE store_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                int rowsDeleted = statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar el elemento en la tabla", ex);
        }

    }
    public boolean doesItemExist(int id) {
        try {
            query = "SELECT * FROM inventory WHERE store_id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Si hay resultados, significa que el registro existe
                        return true;
                    } else {
                        // Si no hay resultados, el registro no existe
                        return false;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Manejo de errores (puedes personalizarlo según tus necesidades)
            return false;
        }
    }
}
