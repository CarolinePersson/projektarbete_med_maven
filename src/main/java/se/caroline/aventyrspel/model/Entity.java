package se.caroline.aventyrspel.model;

public abstract class Entity {
    protected String role;
    protected int health;
    protected int damage;

    public Entity(String role, int health, int damage) {
        this.role = role;
        this.health = health;
        this.damage = damage;
    }

    public String getRole() {
        return role;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void punch(Entity toPunch) {
        toPunch.takeHit(damage);
        System.out.println(role + " slår " + toPunch.getRole() + " för " + damage + " skada.");
    }

    public void takeHit(int damage) {
        health -= damage;
        System.out.println(role + " tar " + damage + " skada! Kvarvarande hälsa: " + health);
    }

    public boolean isConscious() {
        return health > 0;
    }

}
