package enums;

public enum NameMonster {
    DRAGON("Dragon"),
    ORC("Orc"),
    ESKELETON("Eskeleton");

    private final String name;

    NameMonster(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

