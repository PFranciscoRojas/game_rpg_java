package src.inventory;

import enums.Arms;

import java.util.List;

public class Inventory {
    private List<String> armsInventory;
    private static Inventory instance;
    private Inventory() {
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addItem(Arms arms) {
        armsInventory.add(arms.getName());
    }

    public void removeItem(Arms arms) {
        armsInventory.remove(arms.getName());
    }

    public List<String> getItems() {
        return armsInventory;
    }

    public void AllObjectsList() {

        for (String i : armsInventory) {
                System.out.println(i);
        }

    }



}
