package src;

import enums.NameMonster;

public class Monster {
    NameMonster name;
    int level = 5;
    int life = 10;
    double experience = 5.0;
    int force = 5;


    public Monster(NameMonster name) {
       this.name = name;
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


    public int recibirAtaqueCharacter(int force) {

        int lifeDefinitive = this.life - force;
        return lifeDefinitive;
    }
}
