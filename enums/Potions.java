package enums;
import src.Character;

import java.util.Iterator;

public enum Potions  {
        REGENERE(3);

        private final int id;


        Potions(int id){
          this.id = id;

        }

    public int getId() {
        return id;
    }

    public int aplyPotion(Character character,Elements i, int lifeArmadura) {//JAVA OTERATOR REMOVE
        int poder=0;
        int armaduraLife=lifeArmadura;
        if (i.getCategoryId() == Potions.REGENERE.getId()) {

            poder=character.regenerarVida(armaduraLife);
            return poder;
        }
        return poder;
    }
}
