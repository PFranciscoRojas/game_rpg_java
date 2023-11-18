package com.mygdx.game.classGame.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigurationDB {

    private static Connection myConnection;

    public ConfigurationDB() {

    }


    public static Connection getInstance() throws SQLException, IOException {
        if(myConnection == null){
           // Properties properties = new Properties();
            //FileInputStream fileInputStream = new FileInputStream("./core/src/classGame/database/.properties");
           // properties.load(fileInputStream);
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root", "notSecureChangeMe");
        }
        return myConnection;
    }





}
