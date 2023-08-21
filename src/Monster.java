package src;

import enums.NameMonster;

public class Monster implements MainSkills<Character> {
    private NameMonster name;
    private int level;
    private int life;
    private double experience;
    private int force;


    public Monster(NameMonster name) {
        this.name = name;
        this.level=1;
        this.life=400;
        this.force=100;
        this.experience=0;
    }


    public int getLevel() {
        return this.level;
    }

    public int getLife() {
        return this.life;
    }

    public double getExperience() {
        return this.experience;
    }

    public int getForce() {
        return this.force;
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

    @Override
    public void takeDamage(int damage) {
        life-=damage;
    }

    @Override
    public void attack(Character victim) {
        victim.takeDamage(force);
    }
}
