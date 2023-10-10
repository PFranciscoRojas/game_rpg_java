package database;

import enums.Elements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionEquipmentDB {
    private Connection connection = null;
    String query;
    public ConnectionEquipmentDB (){
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
    public List<Elements> listElements() {
        query = "SELECT eqp.*, iny.*, str.* FROM equipment eqp INNER JOIN inventory iny ON eqp.inventory_id = iny.id INNER JOIN  store str ON iny.store_id = str.id;";
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
    public void InsertElement(int id, int idCharacter){
        try {
            connection.setAutoCommit(false);
            query = "INSERT INTO equipment (inventory_id) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.setInt(2, idCharacter);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("La inserción se realizó con éxito.");
                }
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
}
