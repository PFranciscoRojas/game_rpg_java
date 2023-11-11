package classGame.inventory;

import classGame.model.Character;
import classGame.model.Element;
import classGame.repository.InventoryRepository;
import classGame.repository.Repository;

import java.util.List;

public class Inventory {
    Repository<Element> repository;
    Element item;
    String alert = null;
    private static Inventory instance;

    List<Element> inventory;

    private Inventory() throws Exception {
        repository = new InventoryRepository();
        inventory = repository.findAllModel(0);
    }

    public static Inventory getInstance() throws Exception {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public String showInventory() {
        StringBuilder table = new StringBuilder();
        int posicion = 1;
        table.append("               INVENTARIO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Element object : inventory) {
            String attribute = "";
            switch (object.getCategory()) {
                case 1:
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

    public String selectEquipment(int position, Equipment equipment, Character character) throws Exception {
        int posicionAjustada = position - 1;
        if (posicionAjustada >= 0 && posicionAjustada < inventory.size()) {
            item = inventory.get(posicionAjustada);
            return equipment.AddItemToEquipment(item);
        } else {
            return "numero no valido";
        }

        //--->Agregar metodo que hace aumentar al personaje
    }

    public String AddItemInventory(Element item, int idCharacter) throws Exception {
        repository = new InventoryRepository();
        Integer[] dateId = new Integer[2];
        dateId[0] = item.getId();
        dateId[1] = idCharacter;
        if ((long) inventory.size() < 10) {
            if (repository.saveModel(dateId)) {
                inventory.add(item);
                return item.getName();
            }
            return " ----> !!!!  Ya tienes este articulo en el inventario";
        }
        return "Tienes el inventario lleno";
    }

    public String removeItemInventory(int select, Character character) throws Exception {
        repository = new InventoryRepository();
        item = inventory.get(select - 1);
        inventory.remove(item);
        repository.deleteModel(select);
        return item.getName() + " Fue devuelto a la tienda recibiste " + character.removeInventory(item.getGold()) + " de oro por su devolucion";    }

    public boolean hasItemsInInventory() {
        return !inventory.isEmpty();
    }


}

