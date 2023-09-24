package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import enums.Potions;
import src.Character;
import java.util.ArrayList;
import java.util.Iterator;

public class Equipment {
    private static Equipment instance;
    private final int capacidadInicial = 7;
    ArrayList<Elements> MyEquipament = new ArrayList<>(capacidadInicial);
    ArrayList<Armor> amorsType = new ArrayList<>(capacidadInicial);
    private Equipment() {}
    public static Equipment getInstance() {
        if (instance == null) {
            instance = new Equipment();
        }
        return instance;
    }

    //Mostrar Equipment
    public String showEquipament() {
        StringBuilder table = new StringBuilder();
        int posicion = 1;
        table.append("               EQUIPAMENTO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : MyEquipament) {
            String attribute = "";
            switch (object.getClass().getSimpleName()) {
                case "Arms":
                    attribute = "Fuerza: " + ((Arms) object).getForce();
                    break;
                case "Armor":
                    attribute = "Vida: " + ((Armor) object).getlife();
                    break;
                case "Potions":
                    attribute = "Power";
                    break;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
        return table.toString();
    }

    //Metodos que interactuan con el Inventory
    public String returnItemToInventory(int select, Inventory inventory, Character character) {
        Elements item = MyEquipament.get(select);
        if (item instanceof Arms) {
            character.removeArm(((Arms) item).getForce());
        } else if (item instanceof Armor) {
            character.removeArmor(((Armor) item).getlife());
        }
        MyEquipament.remove(item);
        inventory.addItemInventaryToEquipment(item);
        return item.getName() + " Fue devuelto al inventario";
    }

    //Metodos que interactuan con Personaje
    public String AddItemToEquipment(Elements item, Character character) {
        String mss = "";
        String mssAdd = " Fue Agregado a tu Equipo";
        MyEquipament.add(item);
        switch (item.getClass().getSimpleName()) {
            case "Arms":
                character.AddArm(((Arms) item).getForce());
                mss = item.getName() + mssAdd;
                break;
            case "Armor":
                character.AddArmor(((Armor) item).getlife());
                mss = item.getName() + mssAdd;
                amorsType.add((Armor) item);
                break;
            case "Potions":
                mss = item.getName() + mssAdd;
                break;
        }
        return mss;
    }
    public int usePotion(Character character, int vidaArmadura) {//JAVA OTERATOR REMOVE
        int lifeArmadura=vidaArmadura;
        Iterator<Elements> iterador = MyEquipament.iterator();
        while (iterador.hasNext()){
            Elements i = iterador.next();
            if (i instanceof Potions){
                iterador.remove();
              return  ((Potions) i).aplyPotion(character,(Potions) i, lifeArmadura);

            }
        }
        return 0;
    }



    //Verificadores
    public boolean CheckFullEquipment() {
        return (long) MyEquipament.size() < 7;
    }
    public boolean CheckRepeatArmadure(Armor armor) {
        for (Armor existingArmor : amorsType) {
            if (existingArmor.compare(armor)) {
                return false;
            }
        }
        return true;
    }
    public boolean CheckRepeatArm() {
        for (Elements element : MyEquipament) {
            if (element.getClass().getSimpleName().equals("Arms")) {
                return false;
            }
        }
        return true;
    }
    public boolean CheckRepeatPotion() {
        for (Elements element : MyEquipament) {
            if (element.getClass().getSimpleName().equals("Potions")) {
                return false;
            }
        }
        return true;
    }


    public int restablecerVidaConItem(){
        int contadorVidaArmor=0;
        for (Elements element : MyEquipament) {
            if (element instanceof Armor) {
                contadorVidaArmor=contadorVidaArmor+((Armor) element).getlife();
            }
        }
        return contadorVidaArmor;
    }

    public String devolverNombre(){
        String descripcion="";
        for (Elements element : MyEquipament) {
            if (element instanceof Potions) {
                descripcion="Usted tiene una pocima de: "+element.getName() + "\nDescripcion: "+((Potions) element).getDescription();
            }
        }
        return descripcion;
    }

    public boolean existePocima(){
        boolean pocima=false;
        for (Elements element : MyEquipament) {
            if (element instanceof Potions) {
                pocima=true;
            }
        }
        return pocima;
    }

}







