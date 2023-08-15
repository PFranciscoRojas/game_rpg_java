package enums;

enum NameMonster {
    DRAGON("Dragón"),
    ORCO("Orco"),
    ESQUELETO("Esqueleto");

    private final String name;

    NameMonster(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

