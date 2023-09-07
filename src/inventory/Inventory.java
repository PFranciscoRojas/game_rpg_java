package src.inventory;

import enums.Armor;
import enums.Arms;
import src.Character;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    List<Arms> myarms = new ArrayList<>();
    List<Armor> myarmors = new ArrayList<>();

    private Inventory() {

    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void setArm (Arms object){
        myarms.add(object);
    }
    public void setArmor (Armor object){
            myarmors.add(object);
    }

    public String showArmsInventory () {
        StringBuilder listado = new StringBuilder();
        int posicion = 0; // Inicializa el contador de posición
        for (Arms element : myarms) {
            String fila = String.format("| %-2d | %-25s  | %-7s  |%n", posicion,element.getName(),element.getForce());
            listado.append(fila);
            posicion++; // Incrementa la posición para el próximo elemento
        }
        String table = "               MIS ARMAS\n---------------------------------------------\n|    | Nombre                     |  Daño    |\n|----|----------------------------|----------|\n" + listado.toString();
        return table;
    }
    public String showArmorsInventory () {

        StringBuilder listado = new StringBuilder();
        int posicion = 0; // Inicializa el contador de posición
        for (Armor element : myarmors) {
            String fila = String.format("| %-2d | %-25s  | %-7s  |%n", posicion,element.getName(),element.getlife());
            listado.append(fila);
            posicion++; // Incrementa la posición para el próximo elemento
        }
        String table = "               MIS ARMADURAS\n---------------------------------------------\n|    | Nombre                     |Proteccion|\n|----|----------------------------|----------|\n" + listado.toString();
        return table;
    }

    public String selectArm (int select,Equipment equipment,Character character){
        Arms object = myarms.get(select);
        equipment.setArm(object,character);

        return null;
    }

    public String removetArm (int select,Equipment equipment){
        Arms object = myarms.remove(select);


        return null;
    }



}