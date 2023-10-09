package src;

import database.ConnectionStoreDB;
import src.inventory.Inventory;
import src.inventory.Store;

public class MainTest {
    public static void main(String[] args) {
      Store store = Store.getInstance();
      System.out.println(store.showCatalog(store.arms));
      System.out.println(store.showCatalog(store.potions));
    }
}
