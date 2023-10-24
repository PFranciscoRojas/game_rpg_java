package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ConfigurationDB {
    private Properties properties;

    public ConfigurationDB() {

        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("./database/.properties");//Carga propiedades del archivo
            properties.load(fileInputStream);
        } catch (IOException ex) {
            System.out.println("Error de Configuracion");
        }


    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
