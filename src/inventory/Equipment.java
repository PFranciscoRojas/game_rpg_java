package src.inventory;

import enums.Potions;
import src.model.Character;
import src.model.Element;
import src.repository.EquipmentRepository;
import src.repository.Repository;

import java.util.Iterator;
import java.util.List;

import enums.Armor;

public class Equipment {

    private static Equipment instance;
    Element item;
    Repository<Element> repository;
    List<Element> MyEquipament;

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
        character.removeArm(item.getScore());
        MyEquipament.remove(item);
        repository.deleteModel(select);
        inventory.AddItemInventory(item, 1);
        return item.getName() + " Fue devuelto al inventario";

    }

    public String AddItemToEquipment(Element item) throws Exception {
        repository = new EquipmentRepository();
        if ((long) MyEquipament.size() < 7) {
            if (!repository.doesItemExist(item.getType().charAt(0))) {
                Integer[] id = new Integer[1];
                id[0] = item.getId();
                repository.saveModel(id);
                MyEquipament.add(item);
                return "Equipaste " + item.getName();
            }
            return "Ya tienes este tipo de equipacion";
        }
        return "Upss! El equipamento esta lleno";

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







