package database;

import src.Monster;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionMonsterDB {
    private Connection connection = null;

    public ConnectionMonsterDB() {
        ConfigurationDB Setting = new ConfigurationDB();
        String url = Setting.getUrl();
        String user = Setting.getUser();
        String password = Setting.getPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("conection Exitosa");

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

    public List<Monster> listMonsters(int idMision) {
        String consulta = "SELECT mons.*, misi.* FROM monster_mission monsmisi INNER JOIN  monster mons ON monsmisi.monster_id = mons.id INNER JOIN  mission misi ON monsmisi.mission_id = misi.id where monsmisi.mission_id=?";
        List<Monster> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setInt(1, idMision);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Monster object = new Monster();
                    object.setId(resultSet.getInt("mons.id"));
                    object.setName(resultSet.getString("mons.name_monster"));
                    object.setForce(resultSet.getInt("mons.force_monster"));
                    object.setLife(resultSet.getInt("mons.life"));
                    list.add(object);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los monstruos de la mision", ex);
        }

        return list;

    }
}
