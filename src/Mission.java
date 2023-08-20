package src;

public class Mission {
    private String name;
    private  String description;

    private  String recommendedLevel;
    private int goldReward;
    private int experienceReward;

    public Mission(String name, String description, String recommendedLevel, int goldReward, int experienceReward){
        this.name=name;
        this.description=description;
        this.recommendedLevel=recommendedLevel;
        this.goldReward=goldReward;
        this.experienceReward=experienceReward;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }

    public String getRecommendedLevel() {
        return recommendedLevel;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public int getExperienceReward() {
        return experienceReward;
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

    public void setExperienceReward(int experienceReward) {
        this.experienceReward = experienceReward;
    }
}
