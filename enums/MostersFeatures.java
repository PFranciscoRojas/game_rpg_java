package enums;

public enum MostersFeatures {
    DRAGON("Dragon",500,40),
    ORC("Orc",80,25),
    ESKELETON("Eskeleton",50,15);
    private final String name;
    private final int life;
    private final int force;

    MostersFeatures(String name, int life, int force) {
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
