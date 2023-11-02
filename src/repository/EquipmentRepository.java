package src.repository;

import database.ConfigurationDB;
import src.model.Element;
import src.model.Monster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepository implements Repository<Element> {

    private Connection getConnection() throws Exception {
        return ConfigurationDB.getInstance();
    }

    @Override
    public List<Element> findAllModel(int i) throws Exception {
        List<Element> list = new ArrayList<>();
        try (PreparedStatement myStat = getConnection().prepareStatement("SELECT eqp.*, iny.*, str.* FROM equipment eqp INNER JOIN inventory iny ON eqp.inventory_id = iny.id INNER JOIN  store str ON iny.store_id = str.id")) {
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
                "INSERT INTO equipment (inventory_id) SELECT id FROM inventory WHERE store_id = ?" +
                        "AND NOT EXISTS (SELECT 1 FROM equipment WHERE inventory_id = id)")) {
            myStat.setInt(1, (Integer) id[0]);
            int rowsInserted = myStat.executeUpdate();
            return rowsInserted > 0;
        }
    }

    @Override
    public void deleteModel(Integer id) throws Exception {
        try (Connection connection = getConnection();
             PreparedStatement myStat = connection.prepareStatement(
                     "DELETE FROM equipment WHERE id_inventory IN (SELECT id FROM inventory WHERE store_id = ?)")) {
            myStat.setInt(1, id);
            myStat.executeUpdate();
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
        object.setCategory(resultSet.getInt("str.category_id"));
        return object;
    }

    @Override
    public boolean doesItemExist(char Type) throws Exception {
        try (PreparedStatement myStat = getConnection().prepareStatement(
                "SELECT EXISTS (SELECT 1 FROM equipment e " +
                        "INNER JOIN inventory i ON e.inventory_id = i.id" +
                        "INNER JOIN store s ON i.store_id = s.id " +
                        " WHERE s.category = ? )")) {
            try (ResultSet resultSet = myStat.executeQuery()) {
                return resultSet.next();
            }
        }


    }
}
