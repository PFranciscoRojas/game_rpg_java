package com.mygdx.game.classGame.inventory;

import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.model.Element;
import com.mygdx.game.classGame.repository.CharacterRepository;
import com.mygdx.game.classGame.repository.Repository;
import com.mygdx.game.classGame.repository.StoreRepository;

import java.util.Comparator;
import java.util.List;

public class Store {
    Repository<Element> repository;
    CharacterRepository characterRepository;
    String alert = null;
    boolean state = true;
    public List<Element> arms;
    public List<Element> armors;
    public List<Element> potions;
    private static Store instance;

    private Store() throws Exception {
        repository = new StoreRepository();
        arms = repository.findAllModel(1);
        armors = repository.findAllModel(2);
        potions = repository.findAllModel(3);
        armors.sort(Comparator.comparingInt(Element::getGold));
        arms.sort(Comparator.comparingInt(Element::getGold));
        potions.sort(Comparator.comparingInt(Element::getGold));

    }

    public static Store getInstance() throws Exception {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public String showCatalog(List<Element> list) {
        StringBuilder listado = new StringBuilder();
        int posicion = 1; // Inicializa el contador de posición
        String attribute = null;
        String attributeTwo = null;
        for (Element element : list) {
            attribute = "";
            switch (element.getCategory()) {
                case 1:
                    attribute = "Fuerza: ";
                    attributeTwo = "ARMAS";
                    break;
                case 2:
                    attribute = "Vida:   ";
                    attributeTwo = "ARMADURAS";
                    break;
                case 3:
                    attribute = "Power";
                    attributeTwo = "POCIONES";
                    break;
            }
            String fila = String.format("| %-1d | %-6s | %-25s  | %-6s  |%n", posicion, element.getGold(), element.getName(), element.getScore());
            listado.append(fila);
            posicion++; // Incrementa la posición para el próximo elemento
        }

        return "                CATALOGO DE " + attributeTwo + "\n----------------------------------------------------\n|   | Precio | Nombre                     |" + attribute + " |\n|---|--------|----------------------------|---------|\n" + listado.toString();
    }

    public String showScoreElementCatalog(List<Element> list,int index) {
        Element object = list.get(index);
        return switch (object.getCategory()) {
            case 1 -> "DAO: ";
            case 2 -> "PTC: ";
            case 3 -> "PWR : ";
            default -> "No exsite";
        };
    }
    public char buyProduct(int position, Inventory inventory, Character character, List<Element> list) throws Exception {
        characterRepository =new CharacterRepository();
        int posicionAjustada = position - 1;
        if (posicionAjustada >= 0 && posicionAjustada < list.size()) {
            Element object = list.get(posicionAjustada);
            if (character.getGold() >= object.getGold()) {
                character.setGold(character.getGold()-object.getGold());
                return inventory.AddItemInventory(object, 1);
            } else {
                return 'f';//falta dinero
            }
        } else {
            return 'r';//rango
        }

    }
}


