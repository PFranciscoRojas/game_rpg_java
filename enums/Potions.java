package enums;
import src.Character;

import java.util.Iterator;

public enum Potions implements Elements {

        ADRENALIN("Adrenalina",10,5,"Aumenta descontroladamente tu nivel de fuerza"),
        FATALITY("Golpe Fatal",20,10,"Derriva a tu oponente con un K.O"),
        REGENERE("Curacion Instantanea",8,8,"Recupera tu Vida en un Instante");
        private final String name;
        private final String description;
        private final int power;
        private final int gold;

        Potions(String name, int gold, int power,String description){
            this.name = name;
            this.power = power;
            this.gold = gold;
            this.description=description;

        }
        public String getName() {
            return name;
        }
        public int getPower() {
            return power;
        }
        public int getGold() {
            return gold;
        }
        public String getDescription() {
                return description;
        }

    public int aplyPotion(Character character,Potions i, int lifeArmadura) {//JAVA OTERATOR REMOVE
            int poder=0;
            int armaduraLife=lifeArmadura;
                if (i == ADRENALIN) {

                    poder=character.AddArm(i.getPower());
                    return poder;
                }
                if (i == FATALITY) {

                    poder=character.AddArm(i.getPower());
                    return poder;
                }
                if (i == REGENERE) {

                    poder=character.regenerarVida(armaduraLife);
                    return poder;
                }

        return poder;
    }

}
