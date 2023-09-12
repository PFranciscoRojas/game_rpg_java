package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import src.Character;

import java.util.ArrayList;

public class Inventory {
    String alert = null;
    private static Inventory instance;
    private final int capacidadInicial = 7;
    ArrayList<Elements> inventory = new ArrayList<>(capacidadInicial);

    private Inventory() {
    }
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void AddItemInventory(Elements element) {
        inventory.add(element);
    }

    public boolean CheckFullInventory() {
        return (long) inventory.size() < 10;
    }

    public boolean CheckRepeat(Elements element) {
        // El elemento ya está en el inventario, se devuelve false.
        return !inventory.contains(element); // El elemento no está en el inventario, se agrega y se devuelve true.
    }


    public void addItemEquipment(int select,Equipment equipment) {
        Elements item =  equipment.selectItem(select);
        if (inventory.stream().count() < 10) {
            inventory.add(item);
        }
    }
    public String showInventory() {
        StringBuilder table = new StringBuilder();
        int posicion = 0;
        table.append("               INVENTARIO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : inventory) {
            String attribute = "";
            if (object instanceof Arms) {
                String at = Integer.toString(((Arms) object).getForce());
                attribute = " Fuerza:" + at;
            } else if (object instanceof Armor) {
                String at = Integer.toString(((Armor) object).getlife());
                attribute = " Vida:" + at;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
            return table.toString();
        }

    public boolean hasItemsInInventory() {
        return !inventory.isEmpty();
    }

    public String selectEquipment (int position, Equipment equipment , Character character){
        Elements object = inventory.get(position);

        if (equipment.CheckFullEquipment()){
            if (object instanceof Armor && equipment.CheckRepeatArmadure((Armor) object)){
                alert = equipment.AddItemToEquipment(object,character);
                inventory.remove(position);
            }else {
                alert = "Ya tienes Implementada esta prenda de armadura";
            }

            if (object instanceof Arms) {
                alert = equipment.AddItemToEquipment(object,character);
                inventory.remove(position);
            }

        }else {
            alert ="Ya tienes muchas cosas en tu equipo de batalla";
        }

            return alert;
    }

}



