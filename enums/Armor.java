package enums;
import src.Character;

public enum Armor {
    Armor(2);
    private final int id;
    Armor( int id){
        this.id=id;
    }
    public int getId() {
        return id;
    }
}