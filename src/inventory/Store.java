package src.inventory;
import database.ConnectionStoreDB;
import enums.Elements;
import src.Character;
import java.util.Comparator;
import java.util.List;
public class Store {

    ConnectionStoreDB connectionStoreDB = new ConnectionStoreDB();
    String alert = null;
    public List<Elements> arms;
    public List<Elements> armors;
    public List<Elements> potions;
    private static Store instance;
    private Store() {
        arms = connectionStoreDB.listElements('a');
        armors = connectionStoreDB.listElements('b');
        potions = connectionStoreDB.listElements('c');
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
//    Mostrador de Elements
     public String showCatalog (List<Elements> list ) {
         StringBuilder listado = new StringBuilder();
         int posicion = 1; // Inicializa el contador de posición
         String attribute = null;
         String attributeTwo = null;
         for (Elements element : list) {
             attribute = "";
             switch (element.getCategory()) {
                 case "a":
                     attribute = "Fuerza: ";
                     attributeTwo = "ARMAS";
                     break;
                 case "b":
                     attribute = "Vida:   ";
                     attributeTwo = "ARMADURAS";
                     break;
                 case "c":
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

    //Compras de Elements
    public String buyProduct (int position, Inventory inventory , Character character,List<Elements> list ){
        int posicionAjustada=position-1;
        if (posicionAjustada >=0 && posicionAjustada < list.size()){
            Elements object = list.get(posicionAjustada);
            if(character.getGold()>= object.getGold()){
                if (inventory.CheckFullInventory()){
                    ///Verifica si no hay 10 articulos
                    if (inventory.CheckRepeat(object)){
                        character.payArticle(object.getGold());
                        inventory.AddItemInventory(object);
                        alert =  " Compraste " + object.getName() + " Fue Agregada a Tu inventario";
                    } else{
                        alert =  " Ya tienes este articulo en tu inventario";
                    }
                }else {
                    alert =  " Tienes Lleno el Inventario";
                }
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


