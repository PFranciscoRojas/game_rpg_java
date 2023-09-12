package enums;

public enum MonstersFeatures {
    DRAGON("Dragon",500,3),
    ORC("Orc",80,2),
    ESKELETON("Eskeleton",50,1);
    private final String name;
    private final int life;
    private final int force;

    MonstersFeatures(String name, int life, int force) {
        this.name = name;
        this.life = life;
        this.force = force;
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
