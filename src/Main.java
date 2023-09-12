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
        Store tienda = Store.getInstance();///Se crea una unica instancia de la tienda



System.out.println(characterOne.getForce());
        System.out.println(tienda.showCatalogArms());///Mostrar Catalogo Armas
        System.out.println(tienda.showCatalogArmors());///Mostrar Catalogo Armaduras
        System.out.println(tienda.buyArm(0, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArm(1, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArm(3, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(1, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(2, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(3, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(4, inventory,characterOne));//metodo para comprar armas

        System.out.println(tienda.buyArmor(5, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(6, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(7, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(8, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(9, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(10, inventory,characterOne));//metodo para comprar armas
        System.out.println(tienda.buyArmor(5, inventory,characterOne));//metodo para comprar armas
        System.out.println(inventory.showInventory());//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(inventory.selectEquipment(2,equipment,characterOne));//
        System.out.println(equipment.showEquipament());
        System.out.println(inventory.showInventory());//

System.out.println(characterOne.getLife());

//--------------------------------------------------------------Codigo de Prueba----------------------------

        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite un nombre para su cuenta: ");
        String nombre = sc.nextLine();
        Character character = new Character(nombre,"humano","guerrero",100);
        int opcion;
        int mision;
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
                                    System.out.println("Fuerza: "+monstruoUno.getforce());
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

                                            System.out.println("¡ Turno del monstruo para atacar !");
                                            System.out.println("Vida actual monstruo es: "+monstruoUno.getLife());
                                            System.out.println("El mosntruo tipo "+monstruoUno.getName()+" ataca con fuerza de "+monstruoUno.getforce());
                                            character.recibirAtaque(monstruoUno.getforce());
                                            System.out.println("Se redujo -"+ monstruoUno.getforce() +"xp a la vida de tu personaje");
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
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("EQUIPO PARA TU PERSONAJE");
                    System.out.println("¡ En esta seccion podras equipar a tu personaje objetos los cuales te" +
                            " fortaleceran para futuras batallas !");
                    System.out.println("1. Casco");
                    System.out.println("2. Chaleco");
                    System.out.println("3. Botas");
                    System.out.println("4. Piernas");
                    System.out.println("5. Brazos");
                    System.out.println("6. Regresar al menu principal");
                    System.out.println("Digita un numero y elige un articulo para equipar ?");
                    break;
            }
        }while (opcion!=0);
        System.out.println("Salio del juego RPG");
        System.out.println("...Closed...");

    }
}