//package database;
//
//import src.model.Character;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ConnectionCharacterDB {
//    private Connection connection = null;
//
//    public ConnectionCharacterDB() {
//
//        ConfigurationDB Setting = new ConfigurationDB();
//        String url = Setting.getUrl();
//        String user = Setting.getUser();
//        String password = Setting.getPassword();
//
//        try {
//            connection = DriverManager.getConnection(url, user, password);
//            connection.setAutoCommit(false);
//            System.out.println("conection Exitosa");
//
//        } catch (SQLException ex) {
//            // Manejar la excepción de conexión y proporcionar un mensaje descriptivo
//            System.out.println("Error al conectar a la base de datos: ");
//        }
//    }
//
//    public void closeConnection() {
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException ex) {
//
//            throw new RuntimeException("Error al cerrar tabla", ex);
//        }
//    }
//
//    public List<Character> listCharacters() {
//        String consulta = "SELECT * FROM personage";
//        List<Character> list = new ArrayList<>();
//
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    Character character = new Character();
//                    character.setId(resultSet.getInt("id"));
//                    character.setName(resultSet.getString("name_personage"));
//                    character.setBreed(resultSet.getString("breed"));
//                    character.setLevel(resultSet.getInt("level_personage"));
//                    character.setExperience(resultSet.getDouble("experience"));
//                    character.setForce(resultSet.getInt("force_personage"));
//                    character.setGold(resultSet.getInt("gold"));
//                    list.add(character);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al consultar los Elementos", ex);
//        }
//
//        return list;
//    }
//
//    public int verifyCharacter() {
//        int count = 0;
//        String consulta = "SELECT count(*) FROM personage";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    count = resultSet.getInt(1);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al consultar los datos", ex);
//        }
//        return count;
//    }
//
//    public Character getPersonage() {
//        String consulta = "SELECT * FROM personage";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    Character character = new Character();
//                    character.setId(resultSet.getInt("id"));
//                    character.setName(resultSet.getString("name_personage"));
//                    character.setBreed(resultSet.getString("breed"));
//                    character.setLevel(resultSet.getInt("level_personage"));
//                    character.setExperience(resultSet.getDouble("experience"));
//                    character.setLife(resultSet.getInt("life"));
//                    character.setForce(resultSet.getInt("force_personage"));
//                    character.setGold(resultSet.getInt("gold"));
//
//                    return character;
//                }
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al consultar los datos", ex);
//        }
//
//        return null;
//    }
//
//    public void createPersonage(String nombre) {
//        String consulta = "Insert into personage(name_personage,breed,level_personage,experience,force_personage,gold,life)VALUES(?,'Guerrero',1,0,10,100,100)";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            statement.setString(1, nombre);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al crear el personaje", ex);
//        }
//    }
//
//    public void subtractExperienceCharacter(double experience, int idCharacter) {
//        String consulta = "UPDATE personage SET experience=? WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            statement.setDouble(1, experience);
//            statement.setInt(2, idCharacter);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al actualizar la experiencia del personage", ex);
//        }
//    }
//
//    public void levelUpCharacter(int levelCharacter, int idCharacter) {
//        String consulta = "UPDATE personage SET level_personage=? WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            statement.setInt(1, levelCharacter);
//            statement.setInt(2, idCharacter);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al actualizar el nivel del personage", ex);
//        }
//    }
//
//    public void increaseGoldCharacter(int gold, int idCharacter) {
//        String consulta = "UPDATE personage SET gold=? WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            statement.setInt(1, gold);
//            statement.setInt(2, idCharacter);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al aumentar el oro del personage");
//        }
//
//    }
//
//    public void usePotionCharacter(int idPotion) {
//        String consulta = "DELETE FROM equipment  WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            statement.setInt(1, idPotion);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error a eliminar la pocima");
//        }
//
//    }
//
//    public void addExperienceCharacter(double experience, int idCharacter) {
//        String consulta = "UPDATE personage SET experience=? WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
//            statement.setDouble(1, experience);
//            statement.setInt(2, idCharacter);
//            statement.executeUpdate();
//            connection.commit();
//        } catch (SQLException ex) {
//            throw new RuntimeException("Error al actualizar y sumar la experiencia del personage", ex);
//        }
//    }
//}
