package src;

import database.ConnectionCharacterBD;
import database.ConnectionStoreDB;
import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        ConnectionCharacterBD connectionCharacterBD = new ConnectionCharacterBD();
        int count=connectionCharacterBD.verifyCharacter();
        if (count == 0) {
            System.out.println("No hay personaje creado");
            System.out.println("Digite un nombre para su personaje: ");
            String nombre = sc.nextLine();
            connectionCharacterBD.createPersonage(nombre);
            System.out.println(".....Creando Personaje.....");

        }
        System.out.println("Cargando personaje");
        Character character=connectionCharacterBD.getPersonage();
        System.out.println("CARACTERISTICAS DEL PERSONAJE");
        System.out.println("Nombre: "+character.getName()+" - Raza: "+character.getBreed()+" - Vida: "+character.getLife()+" - Fuerza: "+character.getForce()+
                " - Nivel: "+character.getLevel()+" - Experiencia: "+character.getExperience()+" - Oro: "+character.getGold() );
        do {
            System.out.println("MENU DEL JUEGO RPG");
            System.out.println("LEVEL DEL PERSONAJE: "+ character.getLevel());
            System.out.println("CANTIDAD DE ORO: "+character.getGold());
            System.out.println("CANTIDAD DE EXPERIENCIA: "+character.getExperience());
            System.out.println("1. Misiones");
            System.out.println("2. Inventario");
            System.out.println("3. Tienda");
            System.out.println("4. Equipo");
            System.out.println("0. Salir");
            System.out.println(" ¡ Digita el numero para elegir una opcion !");
            opcion = sc.nextInt();
        }while (opcion!=0);
        connectionCharacterBD.closeConnection();
        System.out.println("Salio del juego RPG");
        System.out.println("...Closed...");
        /*Character character = new Character("Esteban");
        Equipment equipment = Equipment.getInstance();
        Inventory inventory = Inventory.getInstance();
        Store store = Store.getInstance();
        System.out.println(store.showCatalog(store.arms));
        System.out.println(store.buyProduct(5,inventory,character,store.arms));
        System.out.println( inventory.showInventory());
        System.out.println(equipment.showEquipament());*/
    }
}
