package com.mygdx.game.classGame.repository;

import com.mygdx.game.classGame.database.ConfigurationDB;
import com.mygdx.game.classGame.model.Mission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MissionRepository implements Repository<Mission> {

    private Connection getConnection() throws Exception {
        return ConfigurationDB.getInstance();
    }
    @Override
    public List<Mission> findAllModel(int i) throws Exception {
       return null;
    }

    @Override
    public Mission getModel(Integer id) throws Exception {
        Mission mission = null;
        try (PreparedStatement myStat= getConnection().prepareStatement("SELECT * FROM mission WHERE id=?")) {
            myStat.setInt(1, id);
            try (ResultSet myResult = myStat.executeQuery()) {
                if( myResult.next()){
                    mission = instanceElement(myResult);
                }
            }
        }
        return mission;
    }

    @Override
    public <T> boolean saveModel(T[] id) throws Exception {//int idCharacter, int opcionMission
        try (PreparedStatement statement = getConnection().prepareStatement("UPDATE mission set personage_id=? WHERE id=?")) {
            statement.setInt(1,(Integer) id[0]);
            statement.setInt(2, (Integer)id[1]);
            statement.executeUpdate();
            return true;
        }
    }

    @Override
    public void deleteModel(Integer id) throws Exception {//Antiguo SETMISSION
        try (PreparedStatement statement = getConnection().prepareStatement( "UPDATE mission set personage_id=NULL WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Mission instanceElement(ResultSet resultSet) throws SQLException {
      Mission mission = new Mission();
        mission.setId(resultSet.getInt("id"));
        mission.setName(resultSet.getString("name_mission"));
        mission.setDescription(resultSet.getString("description_mission"));
        mission.setGoldReward(resultSet.getInt("gold"));
        mission.setExperienceReward(resultSet.getDouble("experience"));
        mission.setPersonage_id(resultSet.getInt("personage_id"));
        return mission;
    }

    @Override
    public boolean doesItemExist(char Type) throws Exception {
        return false;
    }
}
