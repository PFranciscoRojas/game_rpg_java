package enums;
public enum Armor implements Elements {
    HELMETONE("Casco Coraza",10,20,'H'),
    HELMETWO("Casco Porton",6,15,'H'),
    HELMETHREE("Casco Kraken",4,8,'H'),

    CHESTONE("Pecho Coraza",15,30,'C'),
    CHESTWO("Pecho Porton",10,15,'C'),
    CHESTHREE("Pecho Kraken",5,10,'C'),

    PANTSONE("Pantalones Coraza",15,30,'P'),
    PANTSTWO("Pantalones Porton",10,15,'P'),
    PANTSTHREE("Pantalones Kraken",5,10,'P'),

    BOOTSTONE("Botas Coraza",5,10,'B'),
    BOOTSTWO("Botas Porton",3,6,'B'),
    BOOTSTHRE("Botas Kraken",1,3,'B'),

    HANDSONE("Brazoletas Coraza",10,20,'H'),
    HANDSTWO("Brazoletas Porton",6,15,'H'),
    HANDSTHREE("Brazoletas Kraken",3,10,'H');
    private final String name;
    private final int life;
    private final int gold;
    private final char type;
    Armor( String name, int life, int gold,char type){
        this.name = name;
        this.life = life;
        this.gold = gold;
        this.type = type;
   }
    public String getName() {
        return name;
    }
    public int getlife() {
        return life;
    }
    public int getGold() {
        return gold;
    }

    public char getType() {
        return type;
    }


    public boolean compare(Armor o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Armor armor =  o;
        return type == armor.type;
    }
}
