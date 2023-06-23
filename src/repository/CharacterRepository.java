package src.repository;

import database.ConfigurationDB;
import src.model.Character;

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
    public Character instanceElement(ResultSet resultSet) throws SQLException {
        Character character = new Character();
        character.setId(resultSet.getInt("id"));
        character.setName(resultSet.getString("name_personage"));
        character.setBreed(resultSet.getString("breed"));
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
}
