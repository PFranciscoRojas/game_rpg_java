package com.mygdx.game.classGame.inventory;

import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.model.Element;
import com.mygdx.game.classGame.repository.InventoryRepository;
import com.mygdx.game.classGame.repository.Repository;

import java.util.List;

public class Inventory {
     Repository<Element> repository;
    Element item;
    String alert = null;
    private static Inventory instance;

    public List<Element> ListInventory;

    private Inventory() throws Exception {
        repository = new InventoryRepository();
        ListInventory = repository.findAllModel(0);
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
        for (Element object : ListInventory) {
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

    public String showElementIncentory(int index) {
       Element object = ListInventory.get(index);
        return switch (object.getCategory()) {
            case 1 -> "DAO: ";
            case 2 -> "PTC: ";
            case 3 -> "PWR : ";
            default -> "No exsite";
        };
    }

    public char selectEquipment(int position, Equipment equipment, Character character) throws Exception {
        int posicionAjustada = position - 1;
        if (posicionAjustada >= 0 && posicionAjustada < ListInventory.size()) {
            item = ListInventory.get(posicionAjustada);
            return equipment.AddItemToEquipment(item,character);
        } else {
            return'n';
        }

        //--->Agregar metodo que hace aumentar al personaje
    }

    public char AddItemInventory(Element item, int idCharacter) throws Exception {
        repository = new InventoryRepository();
        Integer[] dateId = new Integer[2];
        dateId[0] = item.getId();
        dateId[1] = idCharacter;
        if ((long) ListInventory.size() < 9) {
            if (repository.saveModel(dateId)) {
                ListInventory.add(item);
                return 'c';//comprado
            }
            return'e';//ya existe
        }
        return 'l';//inventario lleno
    }

    public String removeItemInventory(int select, Character character) throws Exception {
        item = ListInventory.get(select - 1);
        ListInventory.remove(item);
        repository.deleteModel(item.getId());
        character.removeInventory(item.getGold()-4);
        return item.getName() + " Fue devuelto a la tienda recibiste " + character.getGold() + " de oro por su devolucion";    }

    public boolean hasItemsInInventory() {
        return !ListInventory.isEmpty();
    }


}

