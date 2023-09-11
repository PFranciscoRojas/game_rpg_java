package src;
import enums.Armor;
import java.util.ArrayList;
import java.util.List;

public class Character implements MainSkills {
    List<Armor> MyEquipment = new ArrayList<>();
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
    public Character(String name, String breed, String typeClass,int gold) {
        this.name = name;
        this.breed = breed;
        this.typeClass = typeClass;
        this.level=1;
        this.experience=0;
        this.agility=10;
        this.force=100;
        this.life=10;
        this.intelligence=500;
        this.gold = gold;
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
    public int getGold() {return this.gold;}
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
    public void setGold(int gold) {
        this.gold = gold;
    }
    @Override
    public int recibirAtaque(int force) {
     return this.life-=force;
    }
    public int payArticle(int gold){return this.gold+=gold;}
    public int AddArm(int force) {
        return this.force+=force;
    }
    public int AddArmor(int life) {
        return this.life+=life;
    }
    public void setElementEquipment (Armor object ){
        MyEquipment.add(object);
    }
    public String showEquipment() {
        StringBuilder Equipament = new StringBuilder();
        for (Armor element : MyEquipment) {
            String nombre = element.getName();
            String atributo =  "Daño";
            String ElementEquipament = String.format("| %-15s | %-15s |%n", nombre, atributo + ": " );
            Equipament.append(ElementEquipament);
            }
        String tabla = Equipament.toString();
        return "TU EQUIPAMIENTO\n" + tabla;
        }


}
