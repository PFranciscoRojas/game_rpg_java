package src.inventory;

import enums.Arms;
import enums.EnumElement;
import src.Character;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    List<EnumElement> MyElementsInventory = new ArrayList<>();

    private Inventory() {

    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void setElement (EnumElement object){
        MyElementsInventory.add(object);
    }

    public EnumElement getElement (int i){
        return MyElementsInventory.get(i);
    }

    public String showInventory() {
        StringBuilder listadoArmas = new StringBuilder();
        StringBuilder listadoArmaduras = new StringBuilder();

        int posicion = 0; // Inicializa el contador de posición

        for (EnumElement element : MyElementsInventory) {
            String nombre = element.getName();
            String tipo = element.getType();
            String atributo = tipo.equals("A") ? "Daño" : "Protección";
            int valor = tipo.equals("A") ? element.getForce() : element.getlife();

            String fila = String.format("| %-8d | %-15s | %-15s |%n", posicion, nombre, atributo + ": " + valor);

            if (tipo.equals("A")) {
                listadoArmas.append(fila);
            } else {
                listadoArmaduras.append(fila);
            }

            posicion++; // Incrementa la posición para el próximo elemento
        }

        String tablaArmas = "Armas\n---------------------------------\n|          | Nombre          | Atributo        |\n|----------|-----------------|-----------------|\n" + listadoArmas.toString();
        String tablaArmaduras = "Armaduras\n---------------------------------\n|          | Nombre          | Atributo        |\n|----------|-----------------|-----------------|\n" + listadoArmaduras.toString();

        return "TU INVENTARIO\n" + tablaArmas + "\n" + tablaArmaduras;
    }


    public String AddEquipament (int value, Character character){
        String send = null;
        EnumElement object = MyElementsInventory.get(value);
        if (object.getType().equals("A")){
            character.AddArm(object.getForce());
            send =  object.getName() + " El arma " +object.getName()+ " fue Agregado a Tu Equipamiento +  " + object.getForce()+ " de Ataque";
        }else {
            character.AddArmous(object.getlife());
            send =  object.getName() + "La armadura"+object.getName()+" fue Agregada a Tu Equipamiento +  " + object.getlife()+ " de vida";
        }
        character.setElementEquipment(object);
        return object.getName() + " Fue Agregada a Tu Equipamiento para Batallar";
    }

}