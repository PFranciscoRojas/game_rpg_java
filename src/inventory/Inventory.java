package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import enums.Potions;
import src.Character;
import java.util.ArrayList;
public class Inventory {
    String alert = null;
    private static Inventory instance;
    private final int capacidadInicial = 7;
    ArrayList<Elements> inventory = new ArrayList<>(capacidadInicial);
    private Inventory() {}
    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    //Mostrar Inventario
    public String showInventory() {
        StringBuilder table = new StringBuilder();
        int posicion = 0;
        table.append("               INVENTARIO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : inventory) {
            String attribute = "";
            switch (object.getClass().getSimpleName()) {
                case "Arms" :
                    attribute = "Fuerza: " + ((Arms) object).getForce();
                    break;
                case "Armor":
                    attribute = "Vida: " + ((Armor) object).getlife();
                    break;
                case "Potions":
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
    public void addItemInventaryToEquipment(Elements item) {
        if ((long) inventory.size() < 10) {
            inventory.add(item);
        }
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
            if (object instanceof Arms && equipment.CheckRepeatArm()) {
                alert = equipment.AddItemToEquipment(object,character);
                inventory.remove(position);
            }else {
                alert = "Ya tienes Implementada un Arma";
            }
            if (object instanceof Potions && equipment.CheckRepeatPotion()) {
                alert = equipment.AddItemToEquipment(object,character);
                inventory.remove(position);
            }else {
                alert = "Ya tienes Implementada un Pocion";
            }
        }else {
            alert ="Ya tienes muchas cosas en tu equipo de batalla";
        }
        return alert;
    }

    //Metodos Que interactuan con Store
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


