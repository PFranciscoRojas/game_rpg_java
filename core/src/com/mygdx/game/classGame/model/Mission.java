package com.mygdx.game.classGame.model;

public class Mission {

    private int id;
    private String name;
    private String description;
    private String recommendedLevel;
    private int goldReward;
    private double experienceReward;
    private int personage_id;

    public Mission() {

    }

    public Mission(String name, String description, String recommendedLevel, int goldReward, double experienceReward) {
        this.name = name;
        this.description = description;
        this.recommendedLevel = recommendedLevel;
        this.goldReward = goldReward;
        this.experienceReward = experienceReward;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRecommendedLevel() {
        return recommendedLevel;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public double getExperienceReward() {
        return experienceReward;
    }

    public int getPersonage_id() {
        return personage_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecommendedLevel(String recommendedLevel) {
        this.recommendedLevel = recommendedLevel;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public void setExperienceReward(double experienceReward) {
        this.experienceReward = experienceReward;
    }

    public void setPersonage_id(int personage_id) {
        this.personage_id = personage_id;
    }
}