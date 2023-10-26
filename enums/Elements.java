package enums;

public class Elements {
    private int id;
    private String name;
    private String description;
    private int score;
    private int gold;
    private String category;
    private String type;
    private int categoryId;

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public void setCategory(String string) {
        this.category = string;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

}
