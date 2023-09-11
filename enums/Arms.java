package enums;

public enum Arms implements Elements {

    SWORD("Espada Platinum",0,5),
    ARC("Arco Domiante",0,10),
    GRENADE("Granada Metralla ",8,15),
    SLINGSHOT("Resortera Explosiva",8,8),
    HAMMER("Martillo Infinito",0,12),
    GUN("Pistola De LLamas",0,14);
    private final String name;
    private final int gold;
    private final int force;
    Arms (String name,int gold,int force){
        this.name = name;
        this.force = force;
        this.gold=gold;
    }
    public String getName() {
        return name;
    }
    public int getGold() {
        return gold;
    }
    public int getForce() {
        return force;
    }


}
