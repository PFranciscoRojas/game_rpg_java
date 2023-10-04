package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionStoreDB {
    private ConfigurationDB configuracion;
    private Connection connection = null;
    private static ConnectionStoreDB instanciaUnica;
    public static ConnectionStoreDB obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new ConnectionStoreDB();
        }
        return instanciaUnica;
    }
    private ConnectionStoreDB() {

        configuracion = new ConfigurationDB();
        String url = configuracion.getUrl();
        String user = configuracion.getUser();
        String password = configuracion.getPassword();

        try {
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("conection Exitosa");

        } catch (SQLException ex) {
            // Manejar la excepción de conexión y proporcionar un mensaje descriptivo
            System.out.println("Error al conectar a la base de datos: ");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            // Manejar la excepción de cierre de conexión aquí si es necesario
          
        }
    }


}
