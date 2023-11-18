package com.mygdx.game.classGame.model;


import com.mygdx.game.classGame.Static.StaticValues;
import com.mygdx.game.classGame.repository.CharacterRepository;

import java.util.Scanner;

public class Character implements MainSkills {
    //ConnectionCharacterDB connectionCharacterDB = new ConnectionCharacterDB();

    int id;
    private String name;
    private String breed;
    private String typeClass;
    private int level;
    private double experience;
    private int life;
    private int force;
    private int intelligence;
    private int agility;
    private int gold;
    CharacterRepository characterRepository;

    public Character() {

    }
    /*public Character(String name ) {
        this.name = name;
        this.breed = breed;
        this.typeClass = typeClass;
        this.level=1;
        this.experience=0;
        this.agility=10;
        this.force=10;
        this.intelligence=500;
        this.gold = 100;
    }*/

    public int getId() {
        return this.id;
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

    public int getLife(){
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

    public int getGold() {
        return this.gold;
    }

    public void setId(int id) {
        this.id = id;
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
        StaticValues.setTemporaryLifeAdd(life);
        this.life = life;
    }

    public void setForce(int force) {
        StaticValues.setTemporaryForceAdd(force);
        this.force = force;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public int recibirAtaque(int force) {
        return this.life -= force;
    }

    public int payArticle(int gold) {
        return this.gold -= gold;
    }

    public int AddArm(int force) {
        return this.force += force;
    }

    public int AddArmor(int life) {
        return this.life += life;
    }

    public int removeArm(int force) {
        return this.force -= force;
    }

    public int removeArmor(int life) {
        return this.life -= life;
    }

    public int removeInventory(int gold) {
        return  this.gold += gold ;
    }

    public boolean battle(Monster monster, int vidaItem) {
        this.setLife(vidaItem);
        Scanner sc = new Scanner(System.in);
        System.out.println("¡ Has encontrado un mostruo !");
        System.out.println("Batalla iniciada");
        System.out.println("CARACTERISTICAS DEL MONSTRUO");
        System.out.println("Tipo: " + monster.getName() + " - Vida: " + monster.getLife() + "La fuerza base es de : " + monster.getforce());
        System.out.println("....CARGANDO BATTALA....");
        while (this.getLife() > 0 && monster.getLife() > 0) {

            System.out.println("¡ Turno del personaje para atacar !");
            System.out.println("Vida actual del personaje es: " + this.getLife());
            System.out.println("Presiona T para hacer daño al " + monster.getName());
            String Atacar = sc.next();
            if (Atacar.equals("t")) {
                System.out.println("Tu personaje " + this.getName() + " ataca con fuerza de " + this.getForce());
                monster.recibirAtaque(this.getForce());
                System.out.println("Se redujo -" + this.getForce() + " a la vida del monstruo y quedo con " + monster.getLife());
            }
            if (monster.getLife() <= 0) {
                System.out.println("Has derrotado al monstruo");
                System.out.println("Tu personaje quedo " + this.getLife() + "xp de vida");
            } else {
                int attackMonster = monster.calculateAttack();
                System.out.println("¡ Turno del monstruo para atacar !");
                System.out.println("Vida actual monstruo es: " + monster.getLife());
                System.out.println("El mosntruo tipo " + monster.getName() + " ataca con fuerza de " + attackMonster);
                this.recibirAtaque(attackMonster);
                System.out.println("Se redujo -" + attackMonster + " a la vida de tu personaje y quedo con " + this.getLife());
                if (this.getLife() <= 0) {
                    System.out.println("Tu personaje a muerto");
                    System.out.println("Has fracasado la mision");
                    System.out.println("Vuelve a repetir la mision");
                    System.out.println("Game over");
                    return false;
                }
            }
        }
        return true;
    }

    public int regenerarVida(int lifeArmadura) {
        return lifeArmadura + 10;
    }

    public int aumentarOro() {
        int nuevoOro = this.getGold() + 5;
        this.setGold(nuevoOro);
        return nuevoOro;
    }

    public double aumentarExperience(double experiencia) {
        double experienceTotal = this.getExperience() + experiencia;
        this.setExperience(experienceTotal);
        return experienceTotal;
    }

    public double restarExperiencia(double experiencia) {
        if (experiencia >= 5) {
            //aumentarLevel();
            experiencia -= 5;
            return experiencia;
        }
        return experiencia;
    }

    public int aumentarLevel(int level) {
        int newLevel = level + 1;
        //this.setLevel(newLevel);
        return newLevel;
    }


}