package database;
import enums.Elements;
import src.Character;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionCharacterDB {
    private Connection connection = null;
    public ConnectionCharacterDB() {

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
    public List<Character> listCharacters() {
        String consulta = "SELECT * FROM personage";
        List<Character> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Character character = new Character();
                    character.setId(resultSet.getInt("id"));
                    character.setName(resultSet.getString("name_personage"));
                    character.setBreed(resultSet.getString("breed"));
                    character.setLevel(resultSet.getInt("level_personage"));
                    character.setExperience(resultSet.getDouble("experience"));
                    character.setForce(resultSet.getInt("force_personage"));
                    character.setGold(resultSet.getInt("gold"));
                    list.add(character);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los Elementos", ex);
        }

        return list;
    }

    public void createPersonage(int nombre) {
        String insertEquipmentQuery = "INSERT INTO equipment (id) VALUES (?)";
        try (PreparedStatement equipmentStatement = connection.prepareStatement(insertEquipmentQuery)) {
            equipmentStatement.setInt(1, nombre);
            int rowsInserted = equipmentStatement.executeUpdate();
        } catch (SQLException e) {


        }
    }

}
