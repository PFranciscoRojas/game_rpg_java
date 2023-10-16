package src;

import database.ConnectionStoreDB;
import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;

public class MainTest {
    public static void main(String[] args) {
        Character character = new Character("Esteban",1);
        Store store = Store.getInstance();
        Equipment equipment = Equipment.getInstance();
        Inventory inventory = Inventory.getInstance();
        System.out.println(inventory.showInventory());
        System.out.println(inventory.selectEquipment(1,equipment,character));

    }
}
