public class Warrior implements Character{
    private String Weapon;
    private int Strength;

    public Warrior (String Weapon, int Strength){
        this.Weapon=Weapon;
        this.Strength = Strength;
    }

    @Override
    public Character clone() {
        return new Warrior(this.Weapon, this.Strength);
    }

    @Override
    public String toString() {
        return "Warrior with "+ Weapon + "Strength" + Strength;
    }

    public int getStrength() {
        return Strength;
    }

    public void setStrength(int strength) {
        this.Strength = strength;
    }
}
