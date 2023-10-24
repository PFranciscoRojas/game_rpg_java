package database;

import src.Character;
import src.Mission;

import java.sql.*;

public class ConnectionMissionDB {

    private Connection connection = null;

    public ConnectionMissionDB() {
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

    public void chooseMission(int idCharacter, int opcionMission) {
        String consulta = "UPDATE mission set personage_id=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setInt(1, idCharacter);
            statement.setInt(2, opcionMission);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al escoger la mision", ex);
        }
    }

    public Mission getMission(int idMission) {
        String consulta = "SELECT * FROM mission WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setInt(1, idMission);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Mission mission = new Mission();
                    mission.setId(resultSet.getInt("id"));
                    mission.setName(resultSet.getString("name_mission"));
                    mission.setDescription(resultSet.getString("description_mission"));
                    mission.setGoldReward(resultSet.getInt("gold"));
                    mission.setExperienceReward(resultSet.getDouble("experience"));
                    mission.setPersonage_id(resultSet.getInt("personage_id"));
                    return mission;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos de mision", ex);
        }

        return null;
    }

    public void setMission(int opcionMission) {
        String consulta = "UPDATE mission set personage_id=NULL WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setInt(1, opcionMission);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar; es decir eliminar personage en la mision", ex);
        }
    }
}
