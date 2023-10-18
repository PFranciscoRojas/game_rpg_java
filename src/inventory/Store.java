package src.inventory;
import database.ConnectionStoreDB;
import enums.Elements;
import src.Character;
import java.util.Comparator;
import java.util.List;
public class Store {
    ConnectionStoreDB connectionStoreDB;
    String alert = null;
    public List<Elements> arms;
    public List<Elements> armors;
    public List<Elements> potions;
    private static Store instance;
    private Store() {
        connectionStoreDB = new ConnectionStoreDB();
        arms = connectionStoreDB.listElements(1);
        armors = connectionStoreDB.listElements(2);
        potions = connectionStoreDB.listElements(3);
        armors.sort(Comparator.comparingInt(Elements::getGold));
        arms.sort(Comparator.comparingInt(Elements::getGold));
        potions.sort(Comparator.comparingInt(Elements::getGold));
        connectionStoreDB.closeConnection();
    }
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }
    public String showCatalog (List<Elements> list ) {
         StringBuilder listado = new StringBuilder();
         int posicion = 1; // Inicializa el contador de posición
         String attribute = null;
         String attributeTwo = null;
         for (Elements element : list) {
             attribute = "";
             switch (element.getCategory()) {
                 case 1:
                     attribute = "Fuerza: ";
                     attributeTwo = "ARMAS";
                     break;
                 case 2:
                     attribute = "Vida:   ";
                     attributeTwo = "ARMADURAS";
                     break;
                 case 3:
                     attribute = "Power";
                     attributeTwo = "POCIONES";
                     break;
             }
             String fila = String.format("| %-1d | %-6s | %-25s  | %-6s  |%n", posicion, element.getGold(), element.getName(), element.getScore());
             listado.append(fila);
             posicion++; // Incrementa la posición para el próximo elemento
         }

         return "                CATALOGO DE "+attributeTwo+"\n----------------------------------------------------\n|   | Precio | Nombre                     |" + attribute + " |\n|---|--------|----------------------------|---------|\n" + listado.toString();
     }
    public String buyProduct (int position, Inventory inventory , Character character,List<Elements> list ){
        int posicionAjustada=position-1;
        if (posicionAjustada >=0 && posicionAjustada < list.size()){
            Elements object = list.get(posicionAjustada);
            if(character.getGold()>= object.getGold()){
                        character.payArticle(object.getGold());
                        alert = "Compraste " + inventory.AddItemInventory(object,1) ;
            }
            else{
                alert =  " No tienes suficiente oro";
            }
        }else {
            alert = "numero no valido";
        }
        return alert;
    }
}


