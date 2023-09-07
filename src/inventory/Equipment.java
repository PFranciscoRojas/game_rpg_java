package src.inventory;

import enums.Armor;
import enums.Arms;
import src.Character;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    private static Equipment instance;
    private Equipment(){

    }
    public static Equipment getInstance(){
        if (instance == null){
            instance = new Equipment();
        }
        return instance;
    }
    public void setArm (Arms object,Character character){
      character.AddArm(object.getForce());
    }
    public void setArmor (Arms object,Character character){
        character.AddArmor(object.getForce());
    }
}
