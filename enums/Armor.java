package enums;
public enum Armor implements Elements {
    HELMETONE("Casco Coraza",10,20),
    HELMETWO("Casco Porton",6,15),
    HELMETHREE("Casco Kraken",4,8),

    CHESTONE("Pecho Coraza",15,30),
    CHESTWO("Pecho Porton",10,15),
    CHESTHREE("Pecho Kraken",5,10),

    PANTSONE("Pantalones Coraza",15,30),
    PANTSTWO("Pantalones Porton",10,15),
    PANTSTHREE("Pantalones Kraken",5,10),

    BOOTSTONE("Botas Coraza",5,10),
    BOOTSTWO("Botas Porton",3,6),
    BOOTSTHRE("Botas Kraken",1,3),

    HANDSONE("Brazoletas Coraza",10,20),
    HANDSTWO("Brazoletas Porton",6,15),
    HANDSTHREE("Brazoletas Kraken",3,10);
    private final String name;
    private final int life;
    private final int gold;
    Armor( String name, int life, int gold){
        this.name = name;
        this.life = life;
        this.gold = gold;
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

}
