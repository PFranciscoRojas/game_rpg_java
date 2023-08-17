package enums;

public enum Arms {

    SWORD("Sword"),
    ARC("Arc"),
    GRENADE("Frenade");

    private final String name;/////varaiable no cambia de valor

    Arms(String name){this.name=name;}
    public String getName() {
        return name;
    }


}
