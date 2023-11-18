package com.mygdx.game.classGame.repository;


import com.mygdx.game.classGame.database.ConfigurationDB;
import com.mygdx.game.classGame.model.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonsterRepository implements Repository<Monster> {

    private Connection getConnection() throws Exception {
        return ConfigurationDB.getInstance();
    }
    @Override
    public List<Monster> findAllModel(int i) throws Exception {
        List<Monster> list = new ArrayList<>();
        try (PreparedStatement myStat= getConnection().prepareStatement("SELECT mons.*, misi.* FROM monster_mission monsmisi INNER JOIN  monster mons ON monsmisi.monster_id = mons.id INNER JOIN  mission misi ON monsmisi.mission_id = misi.id where monsmisi.mission_id=?")) {
            myStat.setInt(1, i);
            try (ResultSet resultSet = myStat.executeQuery()) {
                while (resultSet.next()) {
                    Monster object = instanceElement(resultSet);
                    list.add(object);
                }
            }
        }
        return list;
    }

    @Override
    public Monster getModel(Integer id) throws Exception {
        return null;
    }

    @Override
    public <T> boolean saveModel(T[] id) throws Exception {
        return false;
    }

    @Override
    public void deleteModel(Integer id) throws Exception {

    }

    @Override
    public Monster instanceElement(ResultSet resultSet) throws SQLException {
        Monster monster = new Monster();
        monster.setId(resultSet.getInt("mons.id"));
        monster.setName(resultSet.getString("mons.name_monster"));
        monster.setForce(resultSet.getInt("mons.force_monster"));
        monster.setLife(resultSet.getInt("mons.life"));
        return  monster;
    }

    @Override
    public boolean doesItemExist(char Type) throws Exception {
        return false;
    }
}
