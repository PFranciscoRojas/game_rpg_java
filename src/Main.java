import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Intro with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Bienvenido al juego RPG");
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite el nombre de su personaje: ");
        String nombre = sc.nextLine();
        Character character = new Character(nombre, "humano", "guerrero");
        int forceCharacter = character.force = 10;
        int lifeCharacter = character.life = 20;
        double experience = character.experience = 0.0;
        Monstruo monstruoUno = new Monstruo();

        System.out.println("Seccion de Ataque");

        while(lifeCharacter > 0 && monstruoUno.life>0) {
            int lifenewmonstruo=monstruoUno.recibirAtaqueCharacter(forceCharacter);
            if (lifenewmonstruo <= 0) {
                System.out.println("Winner");
                ++experience;
                System.out.println("Ganastes puntos de experiencia");
                monstruoUno.life=lifenewmonstruo;
            } else {
                int lifenewcharacter=character.recibirAtaqueMonstruo(monstruoUno.force);
                if (lifenewcharacter <= 0) {
                    System.out.println("Game over");
                    lifeCharacter=lifenewcharacter;
                }
            }
        }

        System.out.println("Continua");
    }
}