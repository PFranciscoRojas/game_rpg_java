package com.mygdx.game.classGame.inventory;

import com.mygdx.game.classGame.Static.Potions;
import com.mygdx.game.classGame.Static.StaticValues;
import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.model.Element;
import com.mygdx.game.classGame.repository.EquipmentRepository;
import com.mygdx.game.classGame.repository.Repository;
import com.mygdx.game.classGame.Static.Armor;

import java.util.Iterator;
import java.util.List;
public class Equipment {

    private static Equipment instance;
    Element item;
    Repository<Element> repository;
    public List<Element> MyEquipament;

    private Equipment() throws Exception {
        repository = new EquipmentRepository();
        MyEquipament = repository.findAllModel(0);
    }

    public static Equipment getInstance() throws Exception {
        if (instance == null) {
            instance = new Equipment();
        }
        return instance;
    }

    public String showEquipament() {
        StringBuilder table = new StringBuilder();
        int posicion = 1;
        table.append("               EQUIPAMENTO\n---------------------------------------------\n|    | Nombre                     | Atributo    |\n|----|----------------------------|-------------|\n");
        for (Element object : MyEquipament) {
            String attribute = "";
            switch (object.getCategory()) {
                case 1:
                    attribute = "Fuerza: " + object.getScore();
                    break;
                case 2:
                    attribute = "Vida: " + object.getScore();
                    break;
                case 3:
                    attribute = "Power";
                    break;
            }
            String fila = String.format("| %-2d | %-25s  | %-10s  |%n", posicion, object.getName(), attribute);
            table.append(fila);
            posicion++;
        }
        return table.toString();
    }

    public String returnItemToInventory(int select, Inventory inventory, Character character) throws Exception {
        repository = new EquipmentRepository();
        item = MyEquipament.get(select - 1);
        StaticValues.setTemporaryLifeAdd(StaticValues.getTemporaryLife() - item.getScore());
        System.out.println(StaticValues.getTemporaryLife());
        character.removeArm(item.getScore());
        MyEquipament.remove(item);
        repository.deleteModel(item.getId());
        inventory.AddItemInventory(item, 1);
        return item.getName() + " Fue devuelto al inventario";

    }

    public char AddItemToEquipment(Element item ,Character character) throws Exception {
        repository = new EquipmentRepository();
        if ((long) MyEquipament.size() < 7) {
           if (!repository.doesItemExist(item.getType().charAt(0))) {
                StaticValues.setTemporaryLifeAdd(StaticValues.getTemporaryLife() + item.getScore());
                Integer[] id = new Integer[1];
                id[0] = item.getId();
                repository.saveModel(id);
                MyEquipament.add(item);
                return 'q' ;//equipado
            }
            return 'e';//existe
        }
        return 'l';//lleno

    }

    public int usePotion(Character character, int vidaArmadura) {//JAVA OTERATOR REMOVE
        int lifeArmadura = vidaArmadura;
        Iterator<Element> iterador = MyEquipament.iterator();
        while (iterador.hasNext()) {
            Element i = iterador.next();
            if (i.getCategory() == Potions.REGENERE.getId()) {
                iterador.remove();
                return Potions.REGENERE.aplyPotion(character, (Element) i, lifeArmadura);

            }
        }
        return 0;
    }


    public int restablecerVidaConItem() {
        int contadorVidaArmor = 0;
        for (Element element : MyEquipament) {
            if (element.getCategory() == Armor.Armor.getId()) {
                contadorVidaArmor = contadorVidaArmor + element.getScore();
            }
        }
        return contadorVidaArmor;
    }

    public String devolverNombre() {
        String descripcion = "";
        for (Element element : MyEquipament) {
            if (element.getCategory() == Potions.REGENERE.getId()) {
                descripcion = "Usted tiene una pocima de: " + element.getName() + "\nDescripcion: " + element.getDescription();
            }
        }
        return descripcion;
    }

    //
    public boolean existePocima() {
        boolean pocima = false;
        for (Element element : MyEquipament) {
            if (element.getCategory() == Potions.REGENERE.getId()) {
                pocima = true;
            }
        }
        return pocima;
    }

    public int getIdPotion() {
        Iterator<Element> iterador = MyEquipament.iterator();
        while (iterador.hasNext()) {
            Element i = iterador.next();
            if (i.getCategory() == Potions.REGENERE.getId()) {

                return i.getId();

            }
        }
        return 0;
    }

}







