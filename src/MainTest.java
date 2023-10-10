package src;

import database.ConnectionStoreDB;
import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;

public class MainTest {
    public static void main(String[] args) {
        Character character = new Character("Esteban");
        Equipment equipment = Equipment.getInstance();
        Inventory inventory = Inventory.getInstance();
        Store store = Store.getInstance();
        System.out.println(store.showCatalog(store.arms));
        System.out.println(store.buyProduct(5,inventory,character,store.arms));
        System.out.println( inventory.showInventory());
        System.out.println(equipment.showEquipament());
    }
}
