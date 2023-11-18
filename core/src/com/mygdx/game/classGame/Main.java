package com.mygdx.game.classGame;

import com.mygdx.game.classGame.inventory.Equipment;
import com.mygdx.game.classGame.inventory.Inventory;
import com.mygdx.game.classGame.inventory.Store;
import com.mygdx.game.classGame.model.Character;
import com.mygdx.game.classGame.model.Mission;
import com.mygdx.game.classGame.model.Monster;
import com.mygdx.game.classGame.repository.CharacterRepository;
import com.mygdx.game.classGame.repository.MissionRepository;
import com.mygdx.game.classGame.repository.MonsterRepository;
import com.mygdx.game.classGame.repository.Repository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Inventory inventory = Inventory.getInstance();
        Store tienda = Store.getInstance();
        int opcion, mision, levelNew, vidaItem, vidaArmaduraTotal, goldNew, opcionTienda, opcionArma, opcionArmadura, opcionPocima, opcionEquipo, itemSeleccionado, regenerar = 0;
        double experienciaNueva = 0;
        String [] nombre  = new String[1];
        Integer [] opciones;
        CharacterRepository repository = new CharacterRepository();
        Repository<Mission> repositoryM = new MissionRepository();
        Repository<Monster>repositoryMs = new MonsterRepository();

        Equipment equipment = Equipment.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al juego RPG");





        if (!repository.doesItemExist('n')) {
            System.out.println("No hay personaje creado");
            System.out.println("Digite un nombre para su personaje: ");
            nombre[0] = sc.nextLine();
            repository.saveModel(nombre);
            System.out.println(".....Creando Personaje.....");

        }
        System.out.println("Cargando personaje");
        Character character = repository.getModel(1);//Poner ID que se dese si hay mas usuarios
        System.out.println("CARACTERISTICAS DEL PERSONAJE");
        System.out.println("Nombre: " + character.getName() + " - Raza: " + character.getBreed() + " - Vida: " + character.getLife() + " - Fuerza: " + character.getForce() +
                " - Nivel: " + character.getLevel() + " - Experiencia: " + character.getExperience() + " - Oro: " + character.getGold());

        //Monster monstruoUno = new Monster(MonstersFeatures.DRAGON);
        do {
            character.setExperience(experienciaNueva);
            System.out.println("MENU DEL JUEGO RPG");
            System.out.println("LEVEL DEL PERSONAJE: " + character.getLevel());
            System.out.println("CANTIDAD DE ORO: " + character.getGold());
            System.out.println("CANTIDAD DE EXPERIENCIA: " + character.getExperience());
            System.out.println("1. Misiones");
            System.out.println("2. Inventario");
            System.out.println("3. Tienda");
            System.out.println("4. Equipo");
            System.out.println("0. Salir");
            System.out.println(" ¡ Digita el numero para elegir una opcion !");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    do {
                        if (character.getExperience() >= 5) {
                            experienciaNueva = character.restarExperiencia(character.getExperience());
                            repository.subtractExperienceCharacter(experienciaNueva,1 );
                            levelNew = character.aumentarLevel(character.getLevel());
                            repository.levelUpCharacter(levelNew, character.getId());
                        }
                        character.setLife(100);
                        vidaItem = equipment.restablecerVidaConItem() + character.getLife();
                        vidaArmaduraTotal = equipment.restablecerVidaConItem();
                        System.out.println("MENU DE MISIONES");
                        System.out.println("¡ En esta seccion enfrentaras aventuras y retos los cuales" +
                                " te brindaran sorpresas para mejorar tu nivel !");
                        System.out.println("1. Mision I");
                        System.out.println("2. Regresar al menu principal");
                        mision = sc.nextInt();

                        switch (mision) {

                            case 1:
                                opciones = new Integer[2];
                                opciones[0]=character.getId();
                                opciones[1]=mision;
                                repositoryM.saveModel(opciones);
                                Mission mission = repositoryM.getModel(mision);
                                List<Monster> monsters = repositoryMs.findAllModel(mision);
                                System.out.println("VIDA DEL PERSONAJE: " + vidaItem);
                                System.out.println("....Load mision I....");
                                System.out.println("Recomendaciones");
                                System.out.println("El nivel del personaje minimo debe ser 1");
                                System.out.println("Nombre de la mision");
                                System.out.println(mission.getName());
                                System.out.println("Descripcion de la mision");
                                System.out.println(mission.getDescription());
                                System.out.println("Recompensa Oro: " + mission.getGoldReward());
                                System.out.println("Recompensa Experiencia: " + mission.getExperienceReward());
                                for (int i = 0; i < monsters.size(); i++) {
                                    Monster monster = monsters.get(i);
                                    boolean characterAlive = character.battle(monster, vidaItem);

                                    if (characterAlive) {
                                        goldNew = character.aumentarOro();
                                        System.out.println("ORO NUEVO: " + goldNew);
                                        //repository.increaseGoldCharacter(goldNew, character.getId());
                                        if (i == 0) {
                                            if (equipment.existePocima()) {
                                                System.out.println(equipment.devolverNombre());
                                                System.out.println("Si desea usarla digite si ó digite No para continuar");
                                                sc.nextLine();
                                                String usarPocima = sc.nextLine();
                                                System.out.println("Valor ingresado en usarPocima: " + usarPocima);
                                                if (usarPocima.equalsIgnoreCase("si")) {
                                                    regenerar = equipment.usePotion(character, vidaArmaduraTotal);
                                                    repository.usePotionCharacter(equipment.getIdPotion());
                                                } else {
                                                    regenerar = character.getLife();
                                                }
                                                character.setLife(regenerar);
                                            }
                                        }

                                        vidaItem = character.getLife();
                                        System.out.println("VIDA REGENERADA ES: " + vidaItem);
                                        if (i == monsters.size() - 1) {
                                            if (!characterAlive) {
                                                break;
                                            } else {
                                                System.out.println("NUEVO ORO FINALIZAR: " + goldNew);
                                                //repository.increaseGoldCharacter(goldNew, character.getId());
                                                System.out.println("COMPLETASTE 100% LA MISION");
                                                System.out.println("Obtuvistes oro: " + mission.getGoldReward() + " y Obtuvistes experiencia: " + mission.getExperienceReward());
                                                int recompensaOro = character.getGold() + mission.getGoldReward();
                                              //  repository.increaseGoldCharacter(recompensaOro, character.getId());
                                                experienciaNueva = character.aumentarExperience(mission.getExperienceReward());
                                                repository.addExperienceCharacter(experienciaNueva, character.getId());
                                                character.setGold(recompensaOro);
                                                repositoryM.deleteModel(mision);
                                            }
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                break;
                        }
                    } while (mision != 2);
                    System.out.println("Salio del menu de misiones");

                    break;
                case 2:
                    System.out.println("MENU GENERAL DEL INVENTARIO");
                    System.out.println("¡ En esta seccion observaras todos los items que posee tu personaje !");
                    System.out.println(inventory.showInventory());
                    break;
                case 3:
                    System.out.println("CATALOGO GENERAL DE LA TIENDA");
                    System.out.println("¡ En esta seccion podras comprar un item para fortalecer tus habilidades" +
                            "como guerrero !");
                    System.out.println("Cantidad de oro: " + character.getGold());
                    do {
                        System.out.println("1. Catalogo Armas");
                        System.out.println("2. Catalogo Armaduras");
                        System.out.println("3. Catalogo de pocimas");
                        System.out.println("4. Salir al menu principal");
                        System.out.println("Ingrese su opcion: ");
                        opcionTienda = sc.nextInt();
                        switch (opcionTienda) {
                            case 1:
                                System.out.println(tienda.showCatalog(tienda.arms));
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("Si no desea comprar Digite el numero 0");

                                System.out.println("¡ ADVERTENCIA !");
                                System.out.println("Cantidad de oro: " + character.getGold());
                                System.out.println("Si desea comprar ingrese el numero del id corespondiente a la arma" + " para comprarla");
                                opcionArma = sc.nextInt();
                                if (opcionArma == 0) {
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                } else {
                                    System.out.println(tienda.buyProduct(opcionArma, inventory, character, tienda.arms));
                                }
                                break;
                            case 2:
                                System.out.println(tienda.showCatalog(tienda.armors));
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("¡ ADVERTENCIA !");
                                System.out.println("Cantidad de oro: " + character.getGold());
                                System.out.println("Si no desea comprar Digite el numero 0");
                                System.out.println("Si desea comprar ingrese el numero del id corespondiente a la armadura" + " para comprarla");
                                opcionArmadura = sc.nextInt();
                                if (opcionArmadura == 0) {
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                } else {
                                    System.out.println(tienda.buyProduct(opcionArmadura, inventory, character, tienda.armors));
                                }
                                break;
                            case 3:
                                System.out.println(tienda.showCatalog(tienda.potions));
                                System.out.println("OPCIONES DE COMPRA");
                                System.out.println("¡ ADVERTENCIA !");
                                System.out.println("Cantidad de oro: " + character.getGold());
                                System.out.println("Si no desea comprar Digite el numero 0");
                                System.out.println("Si desea comprar ingrese el numero del id corespondiente a la pocima" + " para comprarla");
                                opcionPocima = sc.nextInt();
                                if (opcionPocima == 0) {
                                    System.out.println("Usted no realizo ninguna compra");
                                    break;
                                } else {
                                    System.out.println(tienda.buyProduct(opcionPocima, inventory, character, tienda.potions));
                                }
                        }
                    } while (opcionTienda != 4);
                    break;
                case 4:
                    System.out.println("EQUIPAMIENTO PARA TU PERSONAJE");
                    System.out.println("¡ En esta seccion podras equipar a tu personaje objetos los cuales te" +
                            " fortaleceran para futuras batallas !");
                    do {
                        System.out.println("OPCIONES");
                        System.out.println("1. Ver inventario");
                        System.out.println("2. Ver equipo");
                        System.out.println("3. Salir al menu principal");
                        System.out.println("Digita un numero para entrar al inventario: ");
                        opcionEquipo = sc.nextInt();
                        if (opcionEquipo == 1) {
                            if (inventory.hasItemsInInventory()) {
                                System.out.println(inventory.showInventory());
                                System.out.println("OPCIONES DE INVENTARIO");
                                System.out.println("Si no desea equipar Digite el numero 0");
                                System.out.println("Digite el numero del id correspondiente al item para equipar a su personaje: ");
                                itemSeleccionado = sc.nextInt();
                                if (itemSeleccionado==0){
                                    System.out.println("Usted no eligio ningun item");
                                    break;
                                }else {
                                    System.out.println(inventory.selectEquipment(itemSeleccionado, equipment, character));
                                    inventory.removeItemInventory(itemSeleccionado, character);
                                }
                            } else {
                                System.out.println("Usted no posee ningun item en su inventario");
                                System.out.println("¡ Dirijace a la tienda y compre un item !");
                                System.out.println(inventory.showInventory());
                            }
                        } else if (opcionEquipo == 2) {
                            System.out.println(equipment.showEquipament());
                            System.out.println("OPCIONES DE EQUIPO");
                            System.out.println("Si no desea desequipar Digite el numero 0");
                            System.out.println("Digite el numero del id correspondiente al item para desequipar a su personaje: ");
                            int option = sc.nextInt();
                            if (option==0){
                                System.out.println("Usted no desequipo ningun item");
                            }else {
                                System.out.println(equipment.returnItemToInventory(option, inventory, character));
                            }

                        }
                    } while (opcionEquipo != 3);
                    break;
            }
        } while (opcion != 0);

        System.out.println("Salio del juego RPG");
        System.out.println("...Closed...");
    }
}