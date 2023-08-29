package src;

import enums.MostersFeatures;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);

        Character character = new Character("nombre", "humano", "guerrero");
        character.setLife(200);
        character.setForce(10);
        character.setExperience(0.0);

        System.out.println("El jugador inicial es "+character.getName()+
                " con vida "+ character.getLife() + " y fuerza "+ character.getForce());

        //----------------------Enum test---------------------
        Monster monstruoUno = new Monster(MostersFeatures.ORC);
        System.out.println("El primer monstruo a enfrentarse es un "+monstruoUno.getName()+
                            " con vida "+ monstruoUno.getLife());
        Monster monstruoDos = new Monster(MostersFeatures.DRAGON);
        System.out.println("El primer monstruo a enfrentarse es un "+monstruoUno.getName()+
                " con vida "+ monstruoUno.getLife());
        Monster monstruoTres = new Monster(MostersFeatures.ESKELETON);
        System.out.println("El primer monstruo a enfrentarse es un "+monstruoUno.getName()+
                " con vida "+ monstruoUno.getLife());
        //----------------------END---------------------


        while (character.getLife() > 0 && monstruoUno.getLife() > 0) {
            String Atacar = sc.next();
            if (Atacar.equals("t")) {
                monstruoUno.takeDamage(character.getForce());
                System.out.println(monstruoUno.getLife());
                System.out.println("-5xp");
            }

            if (monstruoUno.getLife() <= 0) {
                System.out.println("Winner");
                System.out.println(character.getLife());
                System.out.println("Ganaste puntos de experiencia");
            }
        }
        System.out.println(character.getLife());
    }
}
