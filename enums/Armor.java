package enums;

public enum Armor {
    Armor(1);
    private final int id;
    Armor( int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }
}