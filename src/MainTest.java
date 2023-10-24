package src;

import database.ConnectionStoreDB;
import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;

public class MainTest {
    public static void main(String[] args) {

        Character character = new Character();
        Store store = Store.getInstance();
        Equipment equipment = Equipment.getInstance();
        Inventory inventory = Inventory.getInstance();

    }
}
