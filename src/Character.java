package src;


public class Character implements MainSkills {
    private String name;
    private String breed;
    private String typeClass;
    private int level;
    private double experience;
    private int life;
    private int force;
    private int intelligence;
    private int agility;

    public Character(String name, String breed, String typeClass) {
        this.name = name;
        this.breed = breed;
        this.typeClass = typeClass;
        this.level=1;
        this.experience=0;
        this.agility=10;
        this.force=100;
        this.life=500;
        this.intelligence=500;
    }

    public String getName() {
        return this.name;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getTypeClass() {
        return this.typeClass;
    }

    public int getLevel() {
        return this.level;
    }

    public double getExperience() {
        return this.experience;
    }

    public int getLife() {
        return this.life;
    }

    public int getForce() {
        return this.force;
    }

    public int getAgility() {
        return this.agility;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }


    @Override
    public int recibirAtaque(int force) {
        int forceMonstruo=force;
        int lifeCharacter=this.life;
        int lifeDefinitive=lifeCharacter-forceMonstruo;
        return lifeDefinitive;
    }
}
