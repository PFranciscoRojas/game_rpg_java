package src.inventory;
import enums.EnumElement;
import java.util.ArrayList;
import java.util.List;

public class Store {

    List<EnumElement> Arms = new ArrayList<>();
    List<EnumElement> Armous = new ArrayList<>();

    private static Store instance;

    private Store() {

        for (EnumElement element : EnumElement.values()) {
            switch (element.getType()){
                 case "A": Arms.add(element);
                 break;
                 case "B": Armous.add(element);
                 break;
            }
        }
    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

     public String showCatalogArms () {

         StringBuilder listado = new StringBuilder();
         int posicion = 0; // Inicializa el contador de posición

         for (EnumElement element : Arms) {
             String nombre = element.getName();
             String tipo = element.getType();
             String atributo = tipo.equals("A") ? "Daño" : "Protección";
             int valor = tipo.equals("A") ? element.getForce() : element.getlife();

             String fila = String.format("| %-8d | %-25s | %-15s |%n", posicion, nombre, atributo + ": " + valor);


             listado.append(fila);


             posicion++; // Incrementa la posición para el próximo elemento
         }


         String table = "ARMAS\n---------------------------------------------------------\n|          | Nombre                    | Atributo        |\n|----------|---------------------------|-----------------|\n" + listado.toString();

         return "TU INVENTARIO\n" + table;
     }

    public String showCatalogArmous () {

        StringBuilder listado = new StringBuilder();
        int posicion = 0; // Inicializa el contador de posición

        for (EnumElement element : Armous) {
            String nombre = element.getName();
            String tipo = element.getType();
            String atributo = tipo.equals("A") ? "Daño" : "Protección";
            int valor = tipo.equals("A") ? element.getForce() : element.getlife();

            String fila = String.format("| %-8d | %-25s | %-15s |%n", posicion, nombre, atributo + ": " + valor);


            listado.append(fila);


            posicion++; // Incrementa la posición para el próximo elemento
        }


        String table = "ARMADURAS\n---------------------------------------------------------\n|          | Nombre                    | Atributo        |\n|----------|---------------------------|-----------------|\n" + listado.toString();

        return "TU INVENTARIO\n" + table;
    }

    public String buyArm (int value,Inventory inventory){
        EnumElement object = Arms.get(value);
        inventory.setElement(object);
        return object.getName() + " Fue Agregada a Tu inventario";
    }

    public String buyArmous (int value,Inventory inventory){
        EnumElement object = Armous.get(value);
        inventory.setElement(object);
        return object.getName() + " Fue Agregado a Tu inventario" ;
    }

}
