package com.mygdx.game.classGame.Static;

public enum MonstersFeatures {
    DRAGON(3,"Dragon",500,3),
    ORC(2,"Orc",80,2),
    ESKELETON(1,"Eskeleton",50,1);

    private final int id;


    private final String name;
    private final int life;
    private final int force;

    MonstersFeatures(int id, String name, int life, int force) {
        this.id = id;
        this.name = name;
        this.life = life;
        this.force = force;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getlife() {
        return life;
    }
    public int getForce() {
        return force;
    }
}
