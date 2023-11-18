package com.mygdx.game.classGame.Static;

public class StaticValues {
    private static int temporaryLife;
    private static int temporaryForce;

    public static int getTemporaryForce() {
        return temporaryForce;
    }

    public static void setTemporaryForceAdd(int temporaryForce) {
        StaticValues.temporaryForce += temporaryForce;
    }
    public static void setTemporaryForceRest(int temporaryForce) {
        StaticValues.temporaryForce -= temporaryForce;
    }

    public static int getTemporaryLife() {
        return temporaryLife;
    }

    public static void setTemporaryLifeAdd(int value) {
        temporaryLife += value;
    }
    public static void setTemporaryLifeRest(int value) {
        temporaryLife -= value;
    }
}
