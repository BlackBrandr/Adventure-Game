package Enemies;

import java.util.Random;

public class Enemy {
    int id;
    int health;
    int damage;
    int gold;

    public Enemy(int id, int health, int damage, int gold) {
        this.id = id;
        this.health = health;
        this.damage = damage;
        this.gold = gold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int enemyNumber() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}