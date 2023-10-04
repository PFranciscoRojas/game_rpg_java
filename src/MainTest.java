package src;

import database.ConnectionStoreDB;

public class MainTest {
    public static void main(String[] args) {
        ConnectionStoreDB local =  ConnectionStoreDB.obtenerInstancia();
        local.closeConnection();
    }
}
