package src;

import enums.MonstersFeatures;
import src.inventory.Equipment;
import src.inventory.Inventory;
import src.inventory.Store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int daño;
        Character characterOne = new Character("nombre","humano","guerrero",100);

        Inventory inventory = Inventory.getInstance();
        Equipment equipment = Equipment.getInstance();
        Store tienda = Store.getInstance();
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite un nombre para su cuenta: ");
        String nombre = sc.nextLine();
        Character character = new Character(nombre,"humano","guerrero",100);
        int opcion;
        int mision;
        int opcionTienda;
        int opcionCompra;
        int opcionArma;
        int opcionArmadura;
        int opcionEquipo;
        int itemSeleccionado;
        System.out.println(".....Creando Personaje.....");
        System.out.println("Se ha creado tu personaje");

        System.out.println("CARACTERISTICAS DEL PERSONAJE");

        System.out.println("Nombre: "+nombre);
        System.out.println("Raza: "+character.getBreed());
        System.out.println("Clase: "+character.getTypeClass());
        System.out.println("Vida: "+character.getLife());
        System.out.println("Fuerza: "+character.getForce());
        System.out.println("Inteligencia: "+character.getIntelligence());
        System.out.println("Agilidad: "+character.getAgility());
        System.out.println("Nivel: "+character.getLevel());
        System.out.println("Experiencia: "+character.getExperience());

        Monster monstruoUno = new Monster(MonstersFeatures.DRAGON);
        do {
            System.out.println("MENU DEL JUEGO RPG");
            System.out.println("1. Misiones");
            System.out.println("2. Inventario");
            System.out.println("3. Tienda");
            System.out.println("4. Equipo");
            System.out.println("0. Salir");
            System.out.println(" ¡ Digita el numero para elegir una opcion !");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    do {
                        System.out.println("MENU DE MISIONES");
                        System.out.println("¡ En esta seccion enfrentaras aventuras y retos los cuales" +
                                " te brindaran sorpresas para mejorar tu nivel !");
                        System.out.println("1. Mision I");
                        System.out.println("2. Regresar al menu principal");
                        mision=sc.nextInt();
                        switch (mision){
                            case 1:
                                Mission mission=new Mission("misión 1","bosque perdido","1",5,2);
                                if (monstruoUno.getLife()<=0){
                                    System.out.println("¡ Usted ya no tiene nuevas misiones por realizar !");
                                }else{
                                    System.out.println("....Load mision I....");
                                    System.out.println("Recomendaciones");
                                    System.out.println("El nivel del personaje minimo debe ser 1");
                                    System.out.println("Descripcion de la mision");
                                    System.out.println(mission.getDescription());
                                    System.out.println("Recompensa Oro: "+mission.getGoldReward());
                                    System.out.println("Recompensa Experiencia: "+mission.getExperienceReward());
                                    System.out.println("Batalla encontrada");

                                    System.out.println("¡ Has encontrado un mostruo !");

                                    System.out.println("CARACTERISTICAS DEL MONSTRUO");

                                    System.out.println("Tipo: "+monstruoUno.getName());
                                    System.out.println("Vida: "+monstruoUno.getLife());
                                    System.out.println("La fuerza base es de : "+monstruoUno.getforce());
                                    System.out.println("....CARGANDO BATTALA....");

                                    System.out.println("Batalla iniciada");
                                    while (character.getLife() > 0 && monstruoUno.getLife() > 0) {

                                        System.out.println("¡ Turno del personaje para atacar !");
                                        System.out.println("Vida actual del personaje es: "+character.getLife());
                                        System.out.println("Presiona T para hacer daño al " + monstruoUno.getName());
                                        String Atacar = sc.next();
                                        if (Atacar.equals("t")) {
                                            System.out.println("Tu personaje "+character.getName()+" ataca con fuerza de "+character.getForce());
                                            monstruoUno.recibirAtaque(character.getForce());
                                            System.out.println("Se redujo -" + character.getForce() + "xp a la vida del mosntruo");
                                        }

                                        if (monstruoUno.getLife() <= 0) {
                                            System.out.println("Has derrotado al monstruo");
                                            System.out.println("Felicitaciones ¡ VICTORIA !");
                                            System.out.println("Tu personaje quedo "+character.getLife()+"xp de vida");
                                            character.setExperience(2.0);
                                            int oro = mission.getGoldReward();
                                            System.out.println(" Su personaje obtuvo " + oro + " de Oro");
                                            System.out.println(" Su personaje obtuvo " + character.getExperience() + " de experiencia");
                                        } else {
                                            var attackMonster = monstruoUno.calculateAttack();
                                            System.out.println("¡ Turno del monstruo para atacar !");
                                            System.out.println("Vida actual monstruo es: "+monstruoUno.getLife());
                                            System.out.println("El mosntruo tipo "+monstruoUno.getName()+" ataca con fuerza de "+attackMonster);
                                            character.recibirAtaque(attackMonster);
                                            System.out.println("Se redujo -"+ attackMonster +"xp a la vida de tu personaje");
                                            if (character.getLife()<=0){
                                                System.out.println("Tu personaje a muerto");
                                                System.out.println("Has fracasado la mision");
                                                System.out.println("Vuelve a repetir la mission");
                                                System.out.println("Game over");
                                            }
                                        }
                                    }
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
                    do {
                        System.out.println("1. Catalogo Armas");
                        System.out.println("2. Catalogo Armaduras");
                        System.out.println("3. Salir al menu principal");
                        System.out.println("Ingrese su opcion: ");
                        opcionTienda=sc.nextInt();
                        switch (opcionTienda){
                            case 1:
                                System.out.println(tienda.showCatalogArms());
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("Si desea comprar Digite el numero 1");
                                System.out.println("Si no desea comprar Digite el numero 2");
                                opcionCompra=sc.nextInt();
                                if (opcionCompra==1){
                                    System.out.println("¡ ADVERTENCIA !");
                                    System.out.println("Si desea comprar ingrese el numero del id corespondiente a la arma" +
                                            " para comprarla");
                                    opcionArma=sc.nextInt();
                                    System.out.println(tienda.buyArm(opcionArma, inventory,character));
                                }else {
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                }
                                break;
                            case  2:
                                System.out.println(tienda.showCatalogArmors());
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("Si desea comprar Digite el numero 1");
                                System.out.println("Si no desea comprar Digite el numero 2");
                                opcionCompra=sc.nextInt();
                                switch (opcionCompra){
                                    case 1:
                                        System.out.println("¡ ADVERTENCIA !");
                                        System.out.println("Si desea comprar ingrese el numero del id corespondiente a la armadura" +
                                                " para comprarla");
                                        opcionArmadura=sc.nextInt();
                                        System.out.println(tienda.buyArmor(opcionArmadura,inventory,character));
                                        break;
                                    case 2:
                                        System.out.println("Usted no realizo ninguna compra");
                                        break;
                                }
                                break;
                        }
                    }while (opcionTienda!=3);
                    break;
                case 4:
                    System.out.println("EQUIPAMIENTO PARA TU PERSONAJE");
                    System.out.println("¡ En esta seccion podras equipar a tu personaje objetos los cuales te" +
                            " fortaleceran para futuras batallas !");
                    System.out.println("OPCIONES");
                    System.out.println("1. Ver inventario");
                    System.out.println("2. Salir al menu principal");
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
                    } else{
                        System.out.println("Salio del menu de Equipamiento");
                        break;
                    }
                    break;
            }
        }while (opcion!=0);
        System.out.println("Salio del juego RPG");
        System.out.println("...Closed...");

    }
}