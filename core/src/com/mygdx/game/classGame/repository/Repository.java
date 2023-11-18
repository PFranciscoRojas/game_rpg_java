package com.mygdx.game.classGame.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
    public interface Repository<T> {
        List<T> findAllModel(int i) throws Exception;

        T getModel(Integer id) throws Exception;

        <T> boolean saveModel(T[] arreglo) throws Exception;

        void deleteModel(Integer id) throws Exception;

        T instanceElement(ResultSet resultSet) throws Exception;

        boolean doesItemExist(char Type) throws Exception;


    }

