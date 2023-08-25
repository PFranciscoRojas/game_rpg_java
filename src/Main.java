package src;

import enums.NameMonster;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el nombre de su personaje: ");
        String nombre = sc.nextLine();
        Character character = new Character(nombre, "humano", "guerrero");
        int forceCharacter = character.force = 5;
        character.life = 20;
        double experience = character.experience = 0.0;
        Monster monstruoUno = new Monster(NameMonster.DRAGON);
        monstruoUno.setLife(15);
        System.out.println("Seccion de Ataque");

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
