package com.mygdx.game.classGame.model;

public class Element {
    private int id;
    private String name;
    private String description;
    private int score;
    private int gold;
    private String graphicsElement;
    private int category;
    private String type;

    public String getGraphicsElement() {
        return graphicsElement;
    }

    public void setGraphicsElement(String graphicsElement) {
        this.graphicsElement = graphicsElement;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Element() {

    }

}
