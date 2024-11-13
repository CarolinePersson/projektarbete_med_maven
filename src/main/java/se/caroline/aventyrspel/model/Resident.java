package se.caroline.aventyrspel.model;

public class Resident extends Entity {
    public Resident() {
        super("Resident", 12, 3);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
