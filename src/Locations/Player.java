package Locations;

import java.util.Scanner;
import Characters.Samurai;
import Characters.Archer;
import Characters.Knight;


public class Player {
    Inventory inventory;
    String name;
    int health;
    int maxHealth; // Maksimum sağlığı takip etmek için
    int gold;
    int damage;

    boolean caveCompleted = false;
    boolean riverCompleted = false;
    boolean forestCompleted = false;

    public Player(Inventory inventory, String name, int health, int gold, int damage) {
        this.inventory = inventory;
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.gold = gold;
        this.damage = damage;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void restoreFullHealth() {
        this.health = this.maxHealth;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isCaveCompleted() {
        return caveCompleted;
    }

    public void setCaveCompleted(boolean caveCompleted) {
        this.caveCompleted = caveCompleted;
    }

    public boolean isRiverCompleted() {
        return riverCompleted;
    }

    public void setRiverCompleted(boolean riverCompleted) {
        this.riverCompleted = riverCompleted;
    }

    public boolean isForestCompleted() {
        return forestCompleted;
    }

    public void setForestCompleted(boolean forestCompleted) {
        this.forestCompleted = forestCompleted;
    }

    public void SelectCharacter(){
        System.out.println("Select a character: ");
        System.out.println("1. Samurai");
        System.out.println("2. Archer");
        System.out.println("3. Knight\n");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input){
            case 1:
                Samurai samurai = new Samurai(1, 21, 5, 15);
                this.setDamage(samurai.getDamage());
                this.setHealth(samurai.getHealth());
                this.setMaxHealth(samurai.getHealth());
                this.setGold(samurai.getGold());
                System.out.println("Samurai selected\n");
                System.out.println("You have " + this.getDamage() + " damage");
                System.out.println("You have " + this.getHealth() + " health");
                System.out.println("You have " + this.getGold() + " gold\n");
                break;
            case 2:
                Archer archer = new Archer(2, 18, 7, 20);
                this.setDamage(archer.getDamage());
                this.setHealth(archer.getHealth());
                this.setMaxHealth(archer.getHealth());
                this.setGold(archer.getGold());
                System.out.println("Archer selected\n");
                System.out.println("You have " + this.getDamage() + " damage");
                System.out.println("You have " + this.getHealth() + " health");
                System.out.println("You have " + this.getGold() + " gold\n");
                break;
            case 3:
                Knight knight = new Knight(3, 24, 8, 5);
                this.setDamage(knight.getDamage());
                this.setHealth(knight.getHealth());
                this.setMaxHealth(knight.getHealth());
                this.setGold(knight.getGold());
                System.out.println("Knight selected\n");
                System.out.println("You have " + this.getDamage() + " damage");
                System.out.println("You have " + this.getHealth() + " health");
                System.out.println("You have " + this.getGold() + " gold \n");
        }
    }
}