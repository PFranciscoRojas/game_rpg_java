package com.mygdx.game.classGame;
import com.mygdx.game.classGame.inventory.Inventory;
import com.mygdx.game.classGame.inventory.Store;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Store store = Store.getInstance();
        Inventory inventory = Inventory.getInstance();
        System.out.println(store.showCatalog(store.arms));
       // System.out.println(inventory.removeItemInventory(1));
    }
}
