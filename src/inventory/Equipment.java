package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import src.Character;
import java.util.ArrayList;

public class Equipment {
    private static Equipment instance;
    private final int capacidadInicial = 7;
    ArrayList<Elements> MyEquipament = new ArrayList<>(capacidadInicial);
    ArrayList<Armor> amorsType = new ArrayList<>(capacidadInicial);

    private Equipment() {

    }

    public static Equipment getInstance() {
        if (instance == null) {
            instance = new Equipment();
        }
        return instance;
    }

    public String AddItemToEquipment(Elements item, Character character) {

        String mss = "";
        String mssAdd = " Fue Agregado a tu Equipo";
        MyEquipament.add(item);
        if (item instanceof Arms) {
            character.AddArm(((Arms) item).getForce());
            mss = item.getName() + mssAdd;

        } else if (item instanceof Armor) {
            character.AddArmor(((Armor) item).getlife());
            mss = item.getName() + mssAdd;
            amorsType.add((Armor)item);
        }
        return mss;
    }


    public String showEquipament() {
        StringBuilder table = new StringBuilder();
        int posicion = 0;
        table.append("               EQUIPAMENTO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Elements object : MyEquipament) {
            String attribute = "";
            if (object instanceof Arms) {
                String at = Integer.toString(((Arms) object).getForce());
                attribute = " Fuerza:" + at;
            } else if (object instanceof Armor) {
                String at = Integer.toString(((Armor) object).getlife());
                attribute = " Vida:" + at;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
        return table.toString();
    }

    public Elements selectItem(int select) {
        Elements item = MyEquipament.get(select);
        MyEquipament.remove(item);
        return item;
    }

    public boolean CheckFullEquipment() {
        return (long) MyEquipament.size() < 7;
    }

    public boolean CheckRepeatArmadure(Armor armor) {
        for (Armor existingArmor : amorsType) {
            if (existingArmor.compare(armor)){
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
}







