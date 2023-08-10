public class Monstruo {
    String name = "Asaltante";
    int level = 5;
    int life = 10;
    double experience = 5.0;
    int force = 5;

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getLife() {
        return this.life;
    }

    public double getExperience() {
        return this.experience;
    }

    public int getForce() {
        return this.force;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public Monstruo() {
    }

    public int recibirAtaqueCharacter(int force) {

        int lifeDefinitive = this.life - force;
        return lifeDefinitive;
    }
}
