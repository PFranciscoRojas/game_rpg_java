package com.mygdx.game.classGame.repository;

import com.mygdx.game.classGame.database.ConfigurationDB;
import com.mygdx.game.classGame.model.Character;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CharacterRepository implements Repository<Character> {
    private Connection getConnection() throws Exception {
        return ConfigurationDB.getInstance();
    }
    @Override
    public List<Character> findAllModel(int i) throws Exception {
        List<Character> list = new ArrayList<>();
        try (PreparedStatement myStat= getConnection().prepareStatement("SELECT * FROM personage")) {
            try (ResultSet resultSet = myStat.executeQuery()) {
                while (resultSet.next()) {
                    Character object = instanceElement(resultSet);
                    list.add(object);
                }
            }
        }
        return list;
    }

    @Override
    public Character getModel(Integer id) throws Exception {
        Character character = null;
        try (PreparedStatement myStat= getConnection().prepareStatement("SELECT * FROM personage WHERE id = ?")) {
            myStat.setInt(1, id);
            try (ResultSet myResult = myStat.executeQuery()) {
                if( myResult.next()){
                    character = instanceElement(myResult);
                }
            }
        }
        return character;
    }

    @Override
    public <T> boolean saveModel(T[] id) throws Exception {

        try (PreparedStatement myStat = getConnection().prepareStatement( "INSERT INTO personage(name_personage,breed,level_personage,experience,force_personage,gold,life)VALUES(?,'Guerrero',1,0,10,100,100)")) {
            myStat.setString(1, (String) id[0]);
            int rowsInserted = myStat.executeUpdate();

            return rowsInserted > 0;
        }
    }

    @Override
    public void deleteModel(Integer id) throws Exception {

    }

    @Override
    public Character instanceElement(ResultSet resultSet) throws Exception {
        Character character = new Character();
        character.setId(resultSet.getInt("id"));
        character.setName(resultSet.getString("name_personage"));
        character.setBreed(resultSet.getString("breed"));
        character.setLife(resultSet.getInt("life"));
        character.setLevel(resultSet.getInt("level_personage"));
        character.setExperience(resultSet.getDouble("experience"));
        character.setForce(resultSet.getInt("force_personage"));
        character.setGold(resultSet.getInt("gold"));
        return character;
    }

    @Override
    public boolean doesItemExist(char Type) throws Exception {
        try (PreparedStatement myStat = getConnection().prepareStatement("SELECT count(*) FROM personage")) {
            try (ResultSet resultSet = myStat.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public void subtractExperienceCharacter(double experience, int idCharacter) {
        try (PreparedStatement statement =  getConnection().prepareStatement("UPDATE personage SET experience=? WHERE id=?")) {
            statement.setDouble(1, experience);
            statement.setInt(2, idCharacter);
            statement.executeUpdate();
            getConnection().commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar la experiencia del personage", ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void levelUpCharacter(int levelCharacter, int idCharacter) {
        try (PreparedStatement statement =  getConnection().prepareStatement( "UPDATE personage SET level_personage=? WHERE id=?")) {
            statement.setInt(1, levelCharacter);
            statement.setInt(2, idCharacter);
            statement.executeUpdate();
            getConnection().commit();
        } catch (Exception ex) {
            throw new RuntimeException("Error al actualizar el nivel del personage", ex);
        }
    }

    public void sale (int gold, int i) {
        try (PreparedStatement selectStatement = getConnection().prepareStatement("SELECT gold FROM personage WHERE id = ?")) {
            selectStatement.setInt(1, i);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int currentGold = resultSet.getInt("gold");

                    try (PreparedStatement updateStatement = getConnection().prepareStatement("UPDATE personage SET gold=? WHERE id=?")) {
                        updateStatement.setInt(1, Math.max(0, currentGold + gold)); // Evita valores negativos
                        updateStatement.setInt(2, i);
                        updateStatement.executeUpdate();

                        // Si estás utilizando autocommit, no es necesario el commit explícito.
                        // getConnection().commit();
                    } catch (SQLException ex) {
                        throw new RuntimeException("Error al actualizar el oro del personaje", ex);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener el oro del personaje", ex);
        }
    }

    public void payElement(int gold, int i) {
        try (PreparedStatement selectStatement = getConnection().prepareStatement("SELECT gold FROM personage WHERE id = ?")) {
            selectStatement.setInt(1, i);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int currentGold = resultSet.getInt("gold");

                    try (PreparedStatement updateStatement = getConnection().prepareStatement("UPDATE personage SET gold=? WHERE id=?")) {
                        updateStatement.setInt(1, Math.max(0, currentGold - gold)); // Evita valores negativos
                        updateStatement.setInt(2, i);
                        updateStatement.executeUpdate();

                        // Si estás utilizando autocommit, no es necesario el commit explícito.
                        // getConnection().commit();
                    } catch (SQLException ex) {
                        throw new RuntimeException("Error al actualizar el oro del personaje", ex);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener el oro del personaje", ex);
        }
    }

    public void usePotionCharacter(int idPotion) {
        try (PreparedStatement statement = getConnection().prepareStatement( "DELETE FROM equipment  WHERE id=?")) {
            statement.setInt(1, idPotion);
            statement.executeUpdate();
            getConnection().commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error a eliminar la pocima");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void addExperienceCharacter(double experience, int idCharacter) {
        try (PreparedStatement statement = getConnection().prepareStatement("UPDATE personage SET experience=? WHERE id=?")) {
            statement.setDouble(1, experience);
            statement.setInt(2, idCharacter);
            statement.executeUpdate();
            getConnection().commit();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar y sumar la experiencia del personage", ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
