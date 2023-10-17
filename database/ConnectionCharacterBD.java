package database;

import enums.Elements;
import src.Character;

import java.sql.*;
import java.util.Scanner;

public class ConnectionCharacterBD {
    private Connection connection = null;

    public ConnectionCharacterBD(){
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

    public int verifyCharacter() {
        int count = 0;
        String consulta = "SELECT count(*) FROM personage";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos", ex);
        }
        return count;
    }

    public Character getPersonage() {
        String consulta = "SELECT * FROM personage";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Character character = new Character();
                    character.setId(resultSet.getInt("id"));
                    character.setName(resultSet.getString("name_personage"));
                    character.setBreed(resultSet.getString("breed"));
                    character.setLevel(resultSet.getInt("level_personage"));
                    character.setExperience(resultSet.getDouble("experience"));
                    character.setLife(resultSet.getInt("life"));
                    character.setForce(resultSet.getInt("force_personage"));
                    character.setGold(resultSet.getInt("gold"));

                    return character;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos", ex);
        }

        return null;
    }

    public void createPersonage(String nombre) {
        String consulta = "Insert into personage(name_personage,breed,level_personage,experience,force_personage,gold,life)VALUES(?,'Guerrero',1,0,10,100,100)";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombre);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al crear el personaje", ex);
        }
    }

}
