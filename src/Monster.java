package src;
import enums.MonstersFeatures;

import java.util.Random;

public class Monster implements MainSkills {
    Random random = new Random();

    private String name;
    private int level;
    private int life;
    private double experience;
    private int force;
    public Monster(MonstersFeatures type) {
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
    public int recibirAtaque(int force) {
       return this.life -= force;
    }

    public int getforce (){
        return this.force;
    }

    public int calculateAttack(){
        int minDamge = 1;
        int maxDamage = 5;
        int randomStrength = this.force + random.nextInt(maxDamage - minDamge + 1) + minDamge;
        return randomStrength;

    }
    public int rewardGold(){
        return 5;
    }

}
