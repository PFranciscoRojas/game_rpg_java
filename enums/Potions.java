package enums;
public enum Potions implements Elements {

        ADRENALIN("Adrenalina",10,5,"Aumenta descontroladamente tu nivel de fuerza"),
        FATALITY("Golpe Fatal",20,10,"Derriva a tu oponente con un K.O"),
        REGENERE("Curacion Instantanea",8,2,"Recupera tu Vida en un Instante");
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

}
