package enums;
public enum EnumElement {
    ///ARMS
    SWORD("A","Espada Platinum",0,5),
    ARC("A","Arco Domiante",0,10),
    GRENADE("A","Granada Metralla ",0,15),
    SLINGSHOT("A","Resortera Explosiva",0,8),
    HAMMER("A","Martillo Infinito",0,12),
    GUN("A","Pistola De LLamas",0,14),
    //ARMOURS
    HELMETONE("B"," Casco Coraza",5,0),
    HELMETWO("B","Casco Porton",6,0),
    HELMETHREE("B","Casco Kraken",7,0),

    CHESTONE("B","Pecho Coraza",15,0),
    CHESTWO("B","Pecho Porton",16,0),
    CHESTHREE("B","Pecho Kraken",17,0),

    PANTSONE("B","Pantalones Coraza",10,0),
    PANTSTWO("B","Pantalones Porton",11,0),
    PANTSTHREE("B","Pantalones Kraken",12,0),

    BOOTSTONE("B","Botas Coraza",6,0),
    BOOTSTWO("B","Botas Porton",7,0),
    BOOTSTHREE("B","Botas Kraken",8,0),

    HANDSONE("B","Brazoletas Coraza",5,0),
    HANDSTWO("B","Brazoletas Porton",6,0),
    HANDSTHREE("B","Brazoletas Kraken",7,0);

    //POTIONS








    private final String type;
    private final String name;
    private final int life;
    private final int force;


    EnumElement (String type,String name,int life,int force){
        this.name = name;
        this.life = life;
        this.force = force;
        this.type=type;

   }
    public String getType() {
        return type;
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
