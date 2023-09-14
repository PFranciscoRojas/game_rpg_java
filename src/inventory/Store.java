package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Potions;
import src.Character;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Store {


    String alert = null;
    List<Arms> arms = new ArrayList<>();
    List<Armor> armors = new ArrayList<>();
    List<Potions> potions = new ArrayList<>();


    private static Store instance;
    private Store() {
        for (Arms element : Arms.values()){
         arms.add(element);
        }
        for(Armor element : Armor.values()){
            armors.add(element);
        }
        for(Potions element : Potions.values()){
            potions.add(element);
        }
        armors.sort(Comparator.comparingInt(armorsElement -> armorsElement.getGold()));
        arms.sort(Comparator.comparingInt(armsElement -> armsElement.getGold()));
        potions.sort(Comparator.comparingInt(armsElement -> armsElement.getGold()));
    }
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }
     public String showCatalogArms () {
         StringBuilder listado = new StringBuilder();
         int posicion = 0; // Inicializa el contador de posición
         for (Arms element : arms) {
             String fila = String.format("| %-1d | %-6s | %-25s  | %-6s  |%n", posicion,element.getGold(),element.getName(),element.getForce());
             listado.append(fila);
             posicion++; // Incrementa la posición para el próximo elemento
         }
         String table = "                CATALOGO DE ARMAS\n----------------------------------------------------\n|   | Precio | Nombre                     | Daño    |\n|---|--------|----------------------------|---------|\n" + listado.toString();
         return table;
     }
    public String showCatalogArmors () {

        StringBuilder listado = new StringBuilder();
        int posicion = 0; // Inicializa el contador de posición
        for (Armor element : armors) {
            String fila = String.format("| %-2d | %-6s | %-25s  | %-7s  |%n", posicion,element.getGold(),element.getName(),element.getlife());
            listado.append(fila);
            posicion++;
        }
        String table = "               CATALOGO DE ARMADURAS\n-------------------------------------------------------\n|    | Precio | Nombre                     |Proteccion|\n|----|--------|----------------------------|----------|\n" + listado.toString();
        return table;
    }

    public String showCatalogPotions () {

        StringBuilder listado = new StringBuilder();
        int posicion = 0; // Inicializa el contador de posición
        for (Potions element : potions) {
            String fila = String.format("| %-2d | %-6s | %-25s  | %-50s  |%n", posicion,element.getGold(),element.getName(),element.getDescription());
            listado.append(fila);
            posicion++;
        }
        String table = "               CATALOGO DE POSCIONES\n--------------------------------------------------------------------------------------------------\n|    | Precio | Nombre                     | Power                                               |\n|----|--------|----------------------------|-----------------------------------------------------|\n" + listado.toString();
        return table;
    }

    public String buyArm (int position, Inventory inventory , Character character){
        Arms object = arms.get(position);
        if(character.getGold()>= object.getGold()){
            if (inventory.CheckFullInventory()){
                ///Verifica si no hay 10 articulos
                if (inventory.CheckRepeat(object)){
                    character.payArticle(object.getGold());
                   inventory.AddItemInventory(object);
                    alert =  " Compraste " + object.getName() + " Fue Agregada a Tu inventario";
                } else{
                    alert =  " Ya tienes este articulo en tu inventario";
                }
            }else {
                alert =  " Tienes Lleno el Inventario";
            }
        }
        else{
            alert =  " No tienes suficiente oro";
        }
        return alert;
    }
    public String buyArmor (int position, Inventory inventory , Character character) {
        Armor object = armors.get(position);
        if (character.getGold() >= object.getGold()) {
            if (inventory.CheckFullInventory()) {
                ///Verifica si no hay 10 articulos
                if (inventory.CheckRepeat(object)) {
                    character.payArticle(object.getGold());
                    inventory.AddItemInventory(object);
                    alert = " Compraste " + object.getName() + " Fue Agregada a Tu inventario";
                } else {
                    alert = " Ya tienes este articulo en tu inventario";
                }
            } else {
                alert = " Tienes Lleno el Inventario";
            }
        } else {
            alert = " No tienes suficiente oro";
        }
        return alert;
    }

    public String buyPotion (int position, Inventory inventory , Character character){
        Potions object = potions.get(position);
        if(character.getGold()>= object.getGold()){
            if (inventory.CheckFullInventory()){
                if (inventory.CheckRepeat(object)){
                    character.payArticle(object.getGold());
                    inventory.AddItemInventory(object);
                    alert =  " Compraste " + object.getName() + " Fue Agregada a Tu inventario";
                } else{
                    alert =  " Ya tienes este articulo en tu inventario";
                }
            }else {
                alert =  " Tienes Lleno el Inventario";
            }
        }
        else{
            alert =  " No tienes suficiente oro";
        }
        return alert;
    }
}


