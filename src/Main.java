package src;

import database.ConnectionCharacterBD;
import enums.MonstersFeatures;
import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        Inventory inventory = Inventory.getInstance();
        Equipment equipment = Equipment.getInstance();
        Store tienda = Store.getInstance();*/
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        int opcion,mision,opcionTienda,opcionArma,opcionArmadura,opcionPocima,opcionEquipo,itemSeleccionado, regenerar=0;
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

        Monster monstruoUno = new Monster(MonstersFeatures.DRAGON);
        do {
            /*System.out.println("EXPERIENCIA DEL PERSONAJE: "+ character.getExperience());
            double experienciaNueva=character.restarExperiencia(character.getExperience());
            character.setExperience(experienciaNueva);*/
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
            /*switch (opcion){
                case 1:
                    do {
                        character.setLife(10);
                        int vidaItem=equipment.restablecerVidaConItem() + character.getLife();
                        int vidaArmaduraTotal=equipment.restablecerVidaConItem();
                        System.out.println("MENU DE MISIONES");
                        System.out.println("¡ En esta seccion enfrentaras aventuras y retos los cuales" +
                                " te brindaran sorpresas para mejorar tu nivel !");
                        System.out.println("1. Mision I");
                        System.out.println("2. Regresar al menu principal");
                        mision=sc.nextInt();
                        switch (mision){
                            case 1:
                                Mission mission=new Mission("misión 1","bosque perdido","1",3,3);
                                Monster esqueletoUno = new Monster(MonstersFeatures.ESKELETON);
                                Monster esqueletoDos = new Monster(MonstersFeatures.ESKELETON);
                                    System.out.println("VIDA DEL PWEERSONAJE: "+vidaItem);
                                    System.out.println("....Load mision I....");
                                    System.out.println("Recomendaciones");
                                    System.out.println("El nivel del personaje minimo debe ser 1");
                                    System.out.println("Descripcion de la mision");
                                    System.out.println(mission.getDescription());
                                    System.out.println("Recompensa Oro: "+mission.getGoldReward());
                                    System.out.println("Recompensa Experiencia: "+mission.getExperienceReward());
                                    boolean characterAlive = character.battle(esqueletoUno, vidaItem);

                                    if (characterAlive) {
                                        System.out.println("ORO NUEVO: "+character.aumentarOro());
                                        if (equipment.existePocima()){
                                            System.out.println(equipment.devolverNombre());
                                            System.out.println("Si desea usarla digite si ó digite No para continuar");
                                            sc.nextLine();
                                            String usarPocima=sc.nextLine();

                                            System.out.println("Valor ingresado en usarPocima: " + usarPocima);
                                            if (usarPocima.equalsIgnoreCase("si")){
                                                regenerar=equipment.usePotion(character, vidaArmaduraTotal);

                                            }else {
                                                regenerar=character.getLife();
                                            }
                                            character.setLife(regenerar);
                                        }

                                        vidaItem=character.getLife();
                                        System.out.println("VIDA REGENERADA ES: "+vidaItem);
                                        characterAlive=character.battle(esqueletoDos, vidaItem);
                                        if (!characterAlive){
                                            break;
                                        }else {
                                            System.out.println("NUEVO ORO FINALIZAR: "+character.aumentarOro());
                                            System.out.println("COMPLETASTE 100% LA MISION");
                                            System.out.println("Obtuvistes oro: "+mission.getGoldReward()+" y Obtuvistes experiencia: "+mission.getExperienceReward());
                                            int recompensaOro=character.getGold()+mission.getGoldReward();
                                            character.aumentarExperience(mission.getExperienceReward());
                                            character.setGold(recompensaOro);
                                        }
                                    }else {
                                        break;
                                    }
                                break;
                            case 2:
                                break;
                        }
                    }while (mision!=2);
                    System.out.println("Salio del menu de misiones");
                    break;
                case 2:
                    System.out.println("MENU GENERAL DEL INVENTARIO");
                    System.out.println("¡ En esta seccion observaras todos los items que posee tu personaje !");
                    System.out.println(inventory.showInventory());
                    break;
                case 3:
                    System.out.println("CATALOGO GENERAL DE LA TIENDA");
                    System.out.println("¡ En esta seccion podras comprar un item para fortalecer tus habilidades" +
                            "como guerrero !");
                    System.out.println("Cantidad de oro: "+character.getGold());
                    do {
                        System.out.println("1. Catalogo Armas");
                        System.out.println("2. Catalogo Armaduras");
                        System.out.println("3. Catalogo de pocimas");
                        System.out.println("4. Salir al menu principal");
                        System.out.println("Ingrese su opcion: ");
                        opcionTienda=sc.nextInt();
                        switch (opcionTienda){
                            case 1:
                                System.out.println(tienda.showCatalogArms());
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("Si no desea comprar Digite el numero 0");

                                System.out.println("¡ ADVERTENCIA !");
                                System.out.println("Cantidad de oro: "+character.getGold());
                                System.out.println("Si desea comprar ingrese el numero del id corespondiente a la arma" + " para comprarla");
                                opcionArma=sc.nextInt();
                                if (opcionArma==0){
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                }else {
                                    System.out.println(tienda.buyArm(opcionArma, inventory,character));
                                }
                                break;
                            case  2:
                                System.out.println(tienda.showCatalogArmors());
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("¡ ADVERTENCIA !");
                                System.out.println("Cantidad de oro: "+character.getGold());
                                System.out.println("Si no desea comprar Digite el numero 0");
                                System.out.println("Si desea comprar ingrese el numero del id corespondiente a la armadura" + " para comprarla");
                                opcionArmadura=sc.nextInt();
                                if (opcionArmadura==0){
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                }else {
                                    System.out.println(tienda.buyArmor(opcionArmadura,inventory,character));
                                }
                                break;
                            case 3:
                                System.out.println(tienda.showCatalogPotions());
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("¡ ADVERTENCIA !");
                                System.out.println("Cantidad de oro: "+character.getGold());
                                System.out.println("Si no desea comprar Digite el numero 0");
                                System.out.println("Si desea comprar ingrese el numero del id corespondiente a la pocima" + " para comprarla");
                                opcionPocima=sc.nextInt();
                                if (opcionPocima==0){
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                }else {
                                    System.out.println(tienda.buyPotion(opcionPocima,inventory,character));
                                }
                        }
                    }while (opcionTienda!=4);
                    break;
                case 4:
                    System.out.println("EQUIPAMIENTO PARA TU PERSONAJE");
                    System.out.println("¡ En esta seccion podras equipar a tu personaje objetos los cuales te" +
                            " fortaleceran para futuras batallas !");
                    do {
                        System.out.println("OPCIONES");
                        System.out.println("1. Ver inventario");
                        System.out.println("2. Ver equipo");
                        System.out.println("3. Salir al menu principal");
                        System.out.println("Digita un numero para entrar al inventario: ");
                        opcionEquipo=sc.nextInt();
                        if (opcionEquipo==1){
                            if (inventory.hasItemsInInventory()){
                                System.out.println(inventory.showInventory());
                                System.out.println("Digite el numero del id correspondiente al item para equipar a su personaje: ");
                                itemSeleccionado=sc.nextInt();
                                System.out.println(inventory.selectEquipment(itemSeleccionado,equipment,character));
                            }else {
                                System.out.println("Usted no posee ningun item en su inventario");
                                System.out.println("¡ Dirijace a la tienda y compre un item !");
                                System.out.println(inventory.showInventory());
                            }
                        } else if (opcionEquipo==2) {
                            System.out.println(equipment.showEquipament());
                            System.out.println("Digite el numero del id correspondiente al item para desequipar a su personaje: ");
                            int option = sc.nextInt();
                            System.out.println(equipment.returnItemToInventory(option,inventory,character));

                        }
                    }while (opcionEquipo!=3);
                    break;
            }*/
        }while (opcion!=0);
        connectionCharacterBD.closeConnection();
        System.out.println("Salio del juego RPG");
        System.out.println("...Closed...");
    }
}