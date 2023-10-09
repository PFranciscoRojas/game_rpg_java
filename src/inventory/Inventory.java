package src.inventory;
import database.ConnectionInventoryDB;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import enums.Potions;
import src.Character;
import java.util.List;
import java.util.ArrayList;
public class Inventory {
    ConnectionInventoryDB connectionInventoryDB = new ConnectionInventoryDB();    String alert = null;
    private static Inventory instance;
    private final int capacidadInicial = 7;
    List<Elements> inventory;
    private Inventory() {
        inventory =  connectionInventoryDB.listElements();
        connectionInventoryDB.closeConnection();
    }
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    //Mostrar Inventario
    public String showInventory() {
        StringBuilder table = new StringBuilder();
       int posicion = 1;
        table.append("               INVENTARIO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : inventory) {
          String attribute = "";
            switch (object.getCategory()) {
              case "a" :
                    attribute = "Daño: " + object.getScore();
                    break;
               case "b":
                    attribute = "Protec: " + object.getScore();
                    break;
                case "c":
                    attribute = "Power";
                    break;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
        return table.toString();
    }

    //Metodos Que interactuan con Equipment
//    public void addItemInventaryToEquipment(Elements item) {
//        if ((long) inventory.size() < 10) {
//            inventory.add(item);
//        }
//    }
//    public String selectEquipment (int position, Equipment equipment , Character character){
//        int posicionAjustada=position-1;
//
//        boolean addedArmor = false;
//        boolean addedArms = false;
//        boolean addedPotion = false;
//
//        if (posicionAjustada >=0 && posicionAjustada < inventory.size()){
//            Elements object = inventory.get(posicionAjustada);
//            if (equipment.CheckFullEquipment()) {
//                if (object instanceof Armor && equipment.CheckRepeatArmadure((Armor) object)) {
//                    equipment.AddItemToEquipment(object, character);
//                    inventory.remove(posicionAjustada);
//                    addedArmor = true;
//                }
//
//                if (object instanceof Arms && equipment.CheckRepeatArm()) {
//                    equipment.AddItemToEquipment(object, character);
//                    inventory.remove(posicionAjustada);
//                    addedArms = true;
//                }
//
//                if (object instanceof Potions && equipment.CheckRepeatPotion()) {
//                    equipment.AddItemToEquipment(object, character);
//                    inventory.remove(posicionAjustada);
//                    addedPotion = true;
//                }
//
//                if (addedArmor) {
//                    alert = "Se ha agregado una prenda de armadura al equipo.";
//                } else if (addedArms) {
//                    alert = "Se ha agregado un arma al equipo.";
//                } else if (addedPotion) {
//                    alert = "Se ha agregado una poción al equipo.";
//                } else {
//                    alert = "No se ha agregado ningún elemento al equipo Seguramente ya tienes equipado un elemento similar.";
//                }
//            } else {
//                alert = "Ya tienes muchas cosas en tu equipo de batalla.";
//            }
//        }else {
//            alert = "Numero no valido";
//        }
//        return alert;
//
//    }
//
//    //Metodos Que interactuan con Store
    public void AddItemInventory(Elements element) {
        inventory.add(element);

    }
    public String removeItemInventory(int select,Character character){
        Elements delete = inventory.get(select);
        inventory.remove(select);
        return delete.getName() + " Fue devuelto a la tienda recibiste " +  character.removeInventory(delete.getGold()) + " de oro por su devolucion";
    }

    //Verificadores
    public boolean CheckFullInventory() {
        return (long) inventory.size() < 10;
    }
    public boolean CheckRepeat(Elements element) {
        return !inventory.contains(element);
    }
    public boolean hasItemsInInventory() {
        return !inventory.isEmpty();
    }

}

