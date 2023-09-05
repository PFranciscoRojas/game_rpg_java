package src;


import enums.EnumElement;
import src.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Character implements MainSkills {


    List<EnumElement> MyEquipment = new ArrayList<>();
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
     return this.life-=force;
    }

    public int AddArm(int force) {
        return this.force+=force;
    }
    public int AddArmous(int life) {
        return this.life+=life;
    }

    public void setElementEquipment (EnumElement object ){
        MyEquipment.add(object);
    }

    public String showEquipment() {

        StringBuilder Equipament = new StringBuilder();

        for (EnumElement element : MyEquipment) {

            String nombre = element.getName();
            String tipo = element.getType();
            String atributo = tipo.equals("A") ? "Daño" : "Protección";
            int valor = tipo.equals("A") ? element.getForce() : element.getlife();

            String ElementEquipament = String.format("| %-15s | %-15s |%n", nombre, atributo + ": " + valor);


            Equipament.append(ElementEquipament);
            }
        String tabla = Equipament.toString();

        return "TU EQUIPAMIENTO\n" + tabla;
        }


}
