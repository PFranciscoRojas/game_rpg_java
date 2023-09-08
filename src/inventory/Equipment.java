package src.inventory;
import enums.Armor;
import enums.Arms;
import enums.Elements;
import src.Character;

import java.util.ArrayList;

public class Equipment {
    private static Equipment instance;

    private final int capacidadInicial = 10;
    ArrayList<Elements> MyEquipament = new ArrayList<>(capacidadInicial);
    private Equipment(){
    }
    public static Equipment getInstance(){
        if (instance == null){
            instance = new Equipment();
        }
        return instance;
    }

    public String AddItemToEquipment(int select ,Inventory inventory,Character character){
        Elements item =  inventory.selectItem(select);
        MyEquipament.add(item);
        if (item instanceof  Arms){

          character.AddArm(((Arms) item).getForce());

        } else if (item instanceof Armor) {

            character.AddArmor(((Armor)item).getlife());
        }
        inventory.sendToInventory(item);
        return item.getName() + "Fue Agregado a tu Inventario";

    }


}
