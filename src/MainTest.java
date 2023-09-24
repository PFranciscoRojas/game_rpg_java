package src;

import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;
public class MainTest {
    public static void main(String[] args) {
        Character characterOne = new Character("nombre","humano","guerrero",100);
        System.out.println(characterOne.getLife());
        System.out.println(characterOne.getForce());
        System.out.println(characterOne.getGold());
        Inventory inventory = Inventory.getInstance();
        Equipment equipment = Equipment.getInstance();
        Store tienda = Store.getInstance();
        System.out.println(tienda.buyPotion(0,inventory,characterOne));
        System.out.println(tienda.buyPotion(2,inventory,characterOne));
        System.out.println(inventory.showInventory());
        System.out.println(inventory.selectEquipment(0,equipment,characterOne));
        System.out.println(equipment.showEquipament());
        //System.out.println(equipment.usePotion(characterOne));
        System.out.println(equipment.showEquipament());
        System.out.println(characterOne.getLife());
        System.out.println(characterOne.getForce());

    }
}
