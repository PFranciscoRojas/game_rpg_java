package src.inventory;
import database.ConnectionEquipmentDB;
import enums.Elements;
import src.Character;

import java.util.Iterator;
import java.util.List;
public class Equipment {
    ConnectionEquipmentDB connectionEquipmentDB;
    private static Equipment instance;
    Elements item;
    List<Elements> MyEquipament;
    private Equipment() {
       connectionEquipmentDB  = new ConnectionEquipmentDB();
       MyEquipament = connectionEquipmentDB.listElements();
       connectionEquipmentDB.closeConnection();
    }
    public static Equipment getInstance() {
        if (instance == null) {
            instance = new Equipment();
        }
        return instance;
    }
    public String showEquipament() {
        StringBuilder table = new StringBuilder();
        int posicion = 1;
        table.append("               EQUIPAMENTO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : MyEquipament) {
            String attribute = "";
            switch (object.getCategory()) {
                case 1 :
                    attribute = "Fuerza: " + object.getScore();
                    break;
                case 2 :
                    attribute = "Vida: " + object.getScore();
                    break;
                case 3 :
                    attribute = "Power";
                    break;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
        return table.toString();
    }
    public String returnItemToInventory(int select, Inventory inventory, Character character) {
        connectionEquipmentDB  = new ConnectionEquipmentDB();
        item = MyEquipament.get(select-1);
        character.removeArm (item.getScore());
             MyEquipament.remove(item);
                connectionEquipmentDB.deleteElement(item.getId());
                connectionEquipmentDB.closeConnection();
                inventory.AddItemInventory(item,1);
             return  item.getName() + " Fue devuelto al inventario";

    }
    public String AddItemToEquipment(Elements item ) {
        connectionEquipmentDB  = new ConnectionEquipmentDB();
        if ((long) MyEquipament.size() < 7) {
            if(!connectionEquipmentDB.doesItemExist(item.getType().charAt(0))){
            connectionEquipmentDB.insertElement(item.getId());
            MyEquipament.add(item);
            connectionEquipmentDB.closeConnection();
            return "Equipaste " + item.getName();
            }
            return "Ya tienes este tipo de equipacion";
        }
        connectionEquipmentDB.closeConnection();
        return  "Upss! El equipamento esta lleno";

    }

    public int usePotion(Character character) {
        Iterator<Elements> iterador = MyEquipament.iterator();
        while (iterador.hasNext()){
            item = iterador.next();
            if (item.getType().charAt(0) == 's'){
                iterador.remove();
                character.setForce(item.getScore());
              return 0;

            }
        }
        return 0;
    }
}







