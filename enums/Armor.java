package enums;
public enum Armor {
    HELMETONE("Casco Coraza",5,0),
    HELMETWO("Casco Porton",6,0),
    HELMETHREE("Casco Kraken",7,8),
    CHESTONE("Pecho Coraza",15,0),
    CHESTWO("Pecho Porton",16,0),
    CHESTHREE("Pecho Kraken",17,0),

    PANTSONE("Pantalones Coraza",10,0),
    PANTSTWO("Pantalones Porton",11,0),
    PANTSTHREE("Pantalones Kraken",12,0),

    BOOTSTONE("Botas Coraza",6,0),
    BOOTSTWO("Botas Porton",7,0),
    BOOTSTHRE("Botas Kraken",8,0),

    HANDSONE("Brazoletas Coraza",5,0),
    HANDSTWO("Brazoletas Porton",6,0),
    HANDSTHREE("Brazoletas Kraken",7,0);
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
