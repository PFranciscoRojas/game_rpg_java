package src.inventory;
import database.ConnectionInventoryDB;
import enums.Elements;
import src.Character;
import java.util.List;
public class Inventory {
    Elements item;
    String alert = null;
    private static Inventory instance;
    ConnectionInventoryDB connectionInventoryDB;
    List<Elements> inventory;
    private Inventory() {
        connectionInventoryDB = new ConnectionInventoryDB();
        inventory =  connectionInventoryDB.listElements();
        connectionInventoryDB.closeConnection();
    }
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }
    public String showInventory() {
        StringBuilder table = new StringBuilder();
       int posicion = 1;
        table.append("               INVENTARIO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : inventory) {
          String attribute = "";
            switch (object.getCategory()) {
              case 1 :
                    attribute = "Daño: " + object.getScore();
                    break;
               case 2:
                    attribute = "Protec: " + object.getScore();
                    break;
                case 3:
                    attribute = "Power : " + object.getScore();
                    break;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
        return table.toString();
    }
    public String selectEquipment (int position, Equipment equipment , Character character){
        int posicionAjustada=position-1;
        if (posicionAjustada >=0 && posicionAjustada < inventory.size()){
             item = inventory.get(posicionAjustada);
                    alert = "Seleccionaste " + equipment.AddItemToEquipment(item)+ " Fue Agregado a Tu equipo";
        }else {
            alert = "numero no valido";
        }
        return alert;
            //--->Agregar metodo que hace aumentar al personaje
    }
    public String AddItemInventory(Elements item,int idCharacter) {
        connectionInventoryDB = new ConnectionInventoryDB();

        if ((long) inventory.size() < 10) {
             if (!connectionInventoryDB.doesItemExist(item.getId())){
                connectionInventoryDB.insertElement(item.getId(),idCharacter);
                inventory.add(item);
                connectionInventoryDB.closeConnection();
                return item.getName();
            }
            return "Ya tienes este articulo en el inventario";
        }
        return "Tienes el inventario lleno";
        }

    public String removeItemInventory(int select,Character character){
        connectionInventoryDB = new ConnectionInventoryDB();
        item =  inventory.get(select-1);
        inventory.remove(item);
        connectionInventoryDB.DeleteElement(item.getId());
        connectionInventoryDB.closeConnection();
        return item.getName() +  " Fue devuelto a la tienda recibiste " +  character.removeInventory(item.getGold()) + " de oro por su devolucion";
    }

    }

