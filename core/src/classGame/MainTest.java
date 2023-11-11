package classGame;
import classGame.database.ConfigurationDB;
import classGame.inventory.Inventory;
import classGame.inventory.Store;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();
        Inventory inventory = Inventory.getInstance();
        System.out.println(store.showCatalog(store.arms));
       // System.out.println(inventory.removeItemInventory(1));
    }
}
