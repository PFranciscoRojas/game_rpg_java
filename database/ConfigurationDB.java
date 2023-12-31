package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("./database/.properties");
            properties.load(fileInputStream);
            myConnection = DriverManager.getConnection( properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
        }
        return myConnection;
    }





}
