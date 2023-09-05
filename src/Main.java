package src;

import enums.MonstersFeatures;
import src.inventory.Inventory;
import src.inventory.Store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Character characterOne = new Character("nombre","humano","guerrero");
        Inventory inventory = Inventory.getInstance();
        Store tienda = Store.getInstance();///Se crea una unica instancia de la tienda
        System.out.println(tienda.showCatalogArms());// es un metodo String donde se muestra todo el catalogo de armas  de la tienda
        System.out.println(tienda.buyArm(0,inventory));//sirve para comprar un arma qeu como parametro recive  numero en el array y el otro parametro es el inventario
        System.out.println(tienda.showCatalogArmous());// es un metodo String donde se muestra todo el catalogo de armaduras  de la tienda
        System.out.println(tienda.buyArmous(0,inventory));//sirve para comprar un armadura seleccionando el numero en el arreglo y el otro parametro es el inventario
        System.out.println(inventory.showInventory());//Este metodo muestra el contenido del invetario en tablas segun armas y armadura para escoger
        System.out.println(inventory.AddEquipament(0,characterOne));//Inventario tiene funcion que agrega cosas al equipo que es una lista del personaje
        System.out.println(inventory.AddEquipament(1,characterOne));
        System.out.println(characterOne.showEquipment());//Muestra lo que el personaje tieen equipado en su equipo
        System.out.println(characterOne.getLife());// Muestro que la vida si se agrego
        //---------------------------------------Este codigo es de prueba no se afecto nada de la logica del personaje se puede borrar cuanod sea implementado en el menu


        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite un nombre para su cuenta: ");
        String nombre = sc.nextLine();
        Character character = new Character(nombre,"humano","guerrero");
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
                                    System.out.println("Fuerza: "+monstruoUno.getForce());
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
                                            System.out.println("El mosntruo tipo "+monstruoUno.getName()+" ataca con fuerza de "+monstruoUno.getForce());
                                            character.recibirAtaque(monstruoUno.getForce());
                                            System.out.println("Se redujo -"+ monstruoUno.getForce() +"xp a la vida de tu personaje");
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