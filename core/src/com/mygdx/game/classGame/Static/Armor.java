package com.mygdx.game.classGame.Static;

public enum Armor {
    Armor(2);
    private final int id;
    Armor( int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }
}