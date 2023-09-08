package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import java.util.ArrayList;


public class Inventory {
    private static Inventory instance;
    private final int capacidadInicial = 10;
    ArrayList<Elements> inventory = new ArrayList<>(capacidadInicial);

    private Inventory() {

    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public Elements selectItem(int select) {
        Elements item = inventory.get(select);
        return  item;
    }

    public void sendToInventory(Elements i) {
        inventory.remove(i.getName());

    }

    public void addItem(Elements element) {
        if (inventory.stream().count() < 10) {
            inventory.add(element);
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
    }



