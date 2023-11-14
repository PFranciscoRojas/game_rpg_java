package classGame.Static;

public class StaticValues {
    private static int temporaryLife;
    private static int temporaryForce;

    public static int getTemporaryForce() {
        return temporaryForce;
    }

    public static void setTemporaryForce(int temporaryForce) {
        StaticValues.temporaryForce = temporaryForce;
    }

    public static int getTemporaryLife() {
        return temporaryLife;
    }

    public static void setTemporaryLife(int value) {
        temporaryLife = value;
    }
}
