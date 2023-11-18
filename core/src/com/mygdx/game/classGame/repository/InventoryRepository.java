package com.mygdx.game.classGame.repository;

import com.mygdx.game.classGame.database.ConfigurationDB;
import com.mygdx.game.classGame.model.Element;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepository implements Repository <Element> {

    private Connection getConnection() throws Exception {
        return ConfigurationDB.getInstance();
    }
    @Override
    public List<Element> findAllModel(int i) throws Exception {
        List<Element> list = new ArrayList<>();
        try (PreparedStatement myStat= getConnection().prepareStatement("SELECT iny.*, str.* FROM inventory iny INNER JOIN store str ON iny.store_id = str.id")) {
            try (ResultSet resultSet = myStat.executeQuery()) {
                while (resultSet.next()) {
                    Element object = instanceElement(resultSet);
                    list.add(object);
                }
            }
        }
        return list;
    }

    @Override
    public Element getModel(Integer id) throws Exception {
        return null;
    }

    @Override
    public <T> boolean saveModel(T[] id) throws Exception {
        try (PreparedStatement myStat = getConnection().prepareStatement(
                "INSERT INTO inventory (store_id, personage_id) " +
                        "SELECT ?, ? " +
                        "WHERE NOT EXISTS (SELECT 1 FROM inventory WHERE store_id = ?)")) {
            myStat.setInt(1, (Integer) id[0]);
            myStat.setInt(2, (Integer) id[1]);
            myStat.setInt(3, (Integer) id[0]);

            int rowsInserted = myStat.executeUpdate();

            return rowsInserted > 0;
        }
    }

    @Override
    public void deleteModel(Integer id) throws Exception {
        // 1. Eliminar las filas relacionadas en equipment
        try (PreparedStatement deleteEquipmentStat = getConnection().prepareStatement("DELETE FROM equipment WHERE inventory_id IN (SELECT id FROM inventory WHERE store_id = ?)")) {
            deleteEquipmentStat.setInt(1, id);
            deleteEquipmentStat.executeUpdate();
        }

        // 2. Eliminar la fila en inventory
        try (PreparedStatement deleteInventoryStat = getConnection().prepareStatement("DELETE FROM inventory WHERE store_id = ?")) {
            deleteInventoryStat.setInt(1, id);
            deleteInventoryStat.executeUpdate();
        }
    }


    @Override
    public Element instanceElement(ResultSet resultSet) throws SQLException {
        Element object = new Element();
        object.setId(resultSet.getInt("str.id"));
        object.setName(resultSet.getString("str.name_item"));
        object.setType(resultSet.getString("str.category"));
        object.setDescription(resultSet.getString("str.description_item"));
        object.setScore(resultSet.getInt("str.score"));
        object.setGold(resultSet.getInt("str.gold"));
        object.setGraphicsElement(resultSet.getString("str.graphics"));
        object.setCategory(resultSet.getInt("str.category_id"));
        return object;
    }

    @Override
    public boolean doesItemExist(char Type) throws Exception {
        return false;
    }
}
