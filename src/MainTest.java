package src;
import database.ConfigurationDB;
import src.inventory.Inventory;
import src.inventory.Store;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();
        Inventory inventory = Inventory.getInstance();
        System.out.println(store.showCatalog(store.arms));
       // System.out.println(inventory.removeItemInventory(1));
    }
}
