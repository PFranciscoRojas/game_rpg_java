package src;
import enums.NameMonster;

public class Monster {
    private NameMonster name;
    private int level;
    private int life;
    private double experience;
    private int force;

    public Monster(NameMonster name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
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
