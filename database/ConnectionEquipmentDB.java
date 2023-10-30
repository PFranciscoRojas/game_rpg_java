package database;

import enums.Elements;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionEquipmentDB {
    private Connection connection = null;
    String query;

    public ConnectionEquipmentDB() {
        ConfigurationDB Setting = new ConfigurationDB();
        String url = Setting.getUrl();
        String user = Setting.getUser();
        String password = Setting.getPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
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
                    object.setType(resultSet.getString("str.category"));
                    object.setCategoryId(resultSet.getInt("str.category_id"));
                    list.add(object);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos", ex);
        }

        return list;

    }

    public void insertElement(int id) {
        String idStore;
        try {
            connection.setAutoCommit(false);
            idStore = "SELECT id FROM inventory WHERE store_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(idStore)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                int inventoryId = -1;

                if (resultSet.next()) {
                    inventoryId = resultSet.getInt("id");
                } else {
                    throw new RuntimeException("No se encontró el registro de inventario para el store_id proporcionado.");
                }

                // Confirmar la transacción de la primera consulta
                connection.commit();

                // Iniciar una nueva transacción para la inserción en la tabla equipment
                connection.setAutoCommit(false);
                String insertEquipmentQuery = "INSERT INTO equipment (inventory_id) VALUES (?)";
                try (PreparedStatement equipmentStatement = connection.prepareStatement(insertEquipmentQuery)) {
                    equipmentStatement.setInt(1, inventoryId);
                    int rowsInserted = equipmentStatement.executeUpdate();
                }

                // Confirmar la transacción de la segunda consulta
                connection.commit();
            }
        } catch (SQLException ex) {
            try {
                connection.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error al realizar rollback de la transacción", rollbackEx);
            }
            throw new RuntimeException("Error al insertar el elemento en la tabla", ex);
        }
    }

    public void deleteElement(int id) {
        String idStore;
        try {
            connection.setAutoCommit(false);
            idStore = "SELECT id FROM inventory WHERE store_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(idStore)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                int inventoryId = -1;

                if (resultSet.next()) {
                    inventoryId = resultSet.getInt("id");
                } else {
                    throw new RuntimeException("No se encontró el registro de inventario para el store_id proporcionado.");
                }

                // Confirmar la transacción de la primera consulta
                connection.commit();

                // Iniciar una nueva transacción para la inserción en la tabla equipment
                connection.setAutoCommit(false);
                String insertEquipmentQuery = "DELETE FROM equipment WHERE inventory_id = ? ";
                try (PreparedStatement equipmentStatement = connection.prepareStatement(insertEquipmentQuery)) {
                    equipmentStatement.setInt(1, inventoryId);
                    int rowsInserted = equipmentStatement.executeUpdate();
                }

                // Confirmar la transacción de la segunda consulta
                connection.commit();
            }
        } catch (SQLException ex) {
            try {
                connection.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error al realizar rollback de la transacción", rollbackEx);
            }
            throw new RuntimeException("Error al insertar el elemento en la tabla", ex);
        }
    }

    public boolean doesItemExist(char Type) {
        try {
            connection.setAutoCommit(false);
            String checkCategoryQuery = "SELECT EXISTS (SELECT 1 FROM equipment e " +
                    "INNER JOIN inventory i ON e.inventory_id = i.id " +
                    "INNER JOIN store s ON i.store_id = s.id " +
                    "WHERE s.category = ? )";
            try (PreparedStatement categoryStatement = connection.prepareStatement(checkCategoryQuery)) {
                categoryStatement.setString(1, String.valueOf(Type));
                ResultSet resultSet = categoryStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Error al realizar rollback de la transacción", rollbackEx);
            }
            throw new RuntimeException("Error al realizar la verificación en la base de datos", ex);
        }

        return false;

    }

}



