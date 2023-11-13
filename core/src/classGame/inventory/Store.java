package classGame.inventory;

import classGame.model.Character;
import classGame.model.Element;
import classGame.repository.CharacterRepository;
import classGame.repository.Repository;
import classGame.repository.StoreRepository;

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

    public char buyProduct(int position, Inventory inventory, Character character, List<Element> list) throws Exception {
        characterRepository =new CharacterRepository();
        int posicionAjustada = position - 1;
        if (posicionAjustada >= 0 && posicionAjustada < list.size()) {
            Element object = list.get(posicionAjustada);
            if (100 >= object.getGold()) {
                characterRepository.payElement(2,1);
                return inventory.AddItemInventory(object, 1);
            } else {
                return 'f';//falta dinero
            }
        } else {
            return 'r';//rango
        }

    }
}

