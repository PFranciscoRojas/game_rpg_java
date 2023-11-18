package com.mygdx.game.classGame.Static;


import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.model.Element;

public enum Potions {
    REGENERE(3);

    private final int id;


    Potions(int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public int aplyPotion(Character character, Element i, int lifeArmadura) {//JAVA OTERATOR REMOVE
        if (i.getCategory() == Potions.REGENERE.getId()) {
            return character.regenerarVida(lifeArmadura);
        }
        return 0;
    }


}
