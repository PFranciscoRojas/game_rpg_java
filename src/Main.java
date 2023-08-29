package src;

import enums.MostersFeactures;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);

        Character character = new Character("nombre", "humano", "guerrero");
        int forceCharacter = character.force = 5;
        character.life = 20;
        double experience = character.experience = 0.0;

        System.out.println("El jugador inicial es "+character.getName()+
                " con vida "+ character.getLife() + " y fuerza "+ character.getForce());

        //----------------------Enum test---------------------
        Monster monstruoUno = new Monster(MostersFeactures.ORC);
        System.out.println("El primer monstruo a enfrentarse es un "+monstruoUno.getName()+
                            " con vida "+ monstruoUno.getLife());
        Monster monstruoDos = new Monster(MostersFeactures.DRAGON);
        System.out.println("El primer monstruo a enfrentarse es un "+monstruoUno.getName()+
                " con vida "+ monstruoUno.getLife());
        Monster monstruoTres = new Monster(MostersFeactures.ESKELETON);
        System.out.println("El primer monstruo a enfrentarse es un "+monstruoUno.getName()+
                " con vida "+ monstruoUno.getLife());
        //----------------------END---------------------


        while (character.getLife() > 0 && monstruoUno.getLife() > 0) {
            String Atacar = sc.next();
            if (Atacar.equals("t")) {
                monstruoUno.takeDamage(forceCharacter);
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
