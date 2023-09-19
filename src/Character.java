package src;
import enums.Armor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public int establecerLife(){
        return this.life=500;
    }

    public boolean  battle(Monster monster, int vidaItem){
        this.setLife(vidaItem);
        Scanner sc=new Scanner(System.in);
        System.out.println("Batalla iniciada");
        System.out.println("¡ Has encontrado un mostruo !");
        System.out.println("CARACTERISTICAS DEL MONSTRUO");
        System.out.println("Tipo: "+monster.getName());
        System.out.println("Vida: "+monster.getLife());
        System.out.println("La fuerza base es de : "+monster.getforce());
        System.out.println("....CARGANDO BATTALA....");
        System.out.println("VIDA DEL PERSONAJE: "+this.getLife());
        while (this.getLife() > 0 && monster.getLife() > 0) {

            System.out.println("¡ Turno del personaje para atacar !");
            System.out.println("Vida actual del personaje es: "+this.getLife());
            System.out.println("Presiona T para hacer daño al " + monster.getName());
            String Atacar = sc.next();
            if (Atacar.equals("t")) {
                System.out.println("Tu personaje "+this.getName()+" ataca con fuerza de "+this.getForce());
                monster.recibirAtaque(this.getForce());
                System.out.println("Se redujo -" + this.getForce() + "xp a la vida del mosntruo");
            }
            if (monster.getLife() <= 0) {
                System.out.println("Has derrotado al monstruo");
                System.out.println("Tu personaje quedo "+this.getLife()+"xp de vida");
            } else {
                var attackMonster = monster.calculateAttack();
                System.out.println("¡ Turno del monstruo para atacar !");
                System.out.println("Vida actual monstruo es: "+monster.getLife());
                System.out.println("El mosntruo tipo "+monster.getName()+" ataca con fuerza de "+attackMonster);
                this.recibirAtaque(attackMonster);
                System.out.println("Se redujo -"+ attackMonster +"xp a la vida de tu personaje");
                if (this.getLife()<=0){
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


}
