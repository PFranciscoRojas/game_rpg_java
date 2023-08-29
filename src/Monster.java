package src;
import enums.MostersFeatures;

public class Monster {
    private String name;
    private int level;
    private int life;
    private double experience;
    private int force;

    public Monster(MostersFeatures type) {
        this.life = type.getlife();
        this.name = type.getName();
        this.force = type.getForce();
    }

    public int getLevel() {
        return level;
    }
    public String getName() {
        return name;
    }
    public int getLife() {
        return life;
    }

    public double getExperience() {
        return experience;
    }

    public int getForce() {
        return force;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void takeDamage(int force) {
        life -= force;
    }

    public void attack(Character character) {
        character.takeDamage(force);
    }
}
