package src;

import enums.MostersFeatures;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite un nombre para su cuenta: ");
        String nombre = sc.nextLine();
        Character character = new Character(nombre,"humano","guerrero");
        int opcion;
        int mision;
        System.out.println("El nombre del personaje es: "+nombre);

        Monster monstruoUno = new Monster(MostersFeatures.DRAGON);
        do {
            System.out.println("Escoja su personaje");
            System.out.println("1. Guerrero");
            System.out.println("0. Salir");
            System.out.println(" ¡ Digita el numero !");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    do {
                        System.out.println("MENU DE MISIONES");
                        System.out.println("1. para empezar una mision");
                        System.out.println("2. Para salir del menu de misiones");
                        mision=sc.nextInt();
                        switch (mision){
                            case 1:
                                Mission mission=new Mission("misión 1","bosque perdido","1",5,2);
                                System.out.println("....Load....");
                                System.out.println("Batalla encontrada");
                                System.out.println("VIDA NORMAL DEL PERSONAJE ES: "+character.getLife());
                                System.out.println("VIDA NORMAL DEL MONSTRUO ES: "+monstruoUno.getLife());
                                System.out.println("BATALLA INICIADA");
                                while (character.getLife() > 0 && monstruoUno.getLife() > 0) {
                                    System.out.println("Presiona T para atacar al " + monstruoUno.getName());
                                    String Atacar = sc.next();
                                    if (Atacar.equals("t")) {
                                        monstruoUno.recibirAtaque(character.getForce());
                                        System.out.println("-" + character.getForce() + "xp");
                                    }

                                    if (monstruoUno.getLife() <= 0) {
                                        System.out.println("Winner");
                                        System.out.println(character.getLife());
                                        character.setExperience(2.0);
                                        System.out.println("Ganaste puntos de experiencia");
                                        int oro = mission.getGoldReward();
                                        System.out.println(" Su personaje obtuvo " + oro + " de Oro");
                                        System.out.println(" Su personaje obtuvo " + character.getExperience() + " de experiencia");
                                    } else {
                                character.recibirAtaque(monstruoUno.getForce());
                                if (character.getLife()<=0){
                                    System.out.println("Game over mision");
                                }
                                System.out.println(character.getLife());

                                    }
                                }
                                break;
                            case 2:
                                break;
                        }
                    }while (mision!=2);
                    System.out.println("Salio del menu de misiones");
                    break;
            }
        }while (opcion!=0);
        System.out.println("Salio del juego RPG");
        System.out.println("...Closed...");

    }
}