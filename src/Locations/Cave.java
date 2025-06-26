package Locations;

import Enemies.Zombie;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cave extends BattleLocation {
    private List<Zombie> zombies;
    private Locations.Player player;
    private int initialZombieCount;
    
    public Cave(Locations.Player player) {
        super("Cave");
        this.player = player;
        this.zombies = new ArrayList<>();
        createZombies();
    }
    
    public int enemyNumber() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
    
    private void createZombies() {
        int zombieCount = enemyNumber();
        this.initialZombieCount = zombieCount;
        
        for (int i = 0; i < zombieCount; i++) {
            Zombie zombie = new Zombie(1, 10, 3, 4);
            zombies.add(zombie);
        }
        
        System.out.println(zombieCount + " zombie(s) spawned in the cave!");
    }
    
    @Override
    public void combat() {
        Scanner scanner = new Scanner(System.in);
        
        while (player.getHealth() > 0 && !zombies.isEmpty()) {

            System.out.println("\n--- Player's Turn ---");
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Remaining zombies: " + zombies.size());
            System.out.println("Press Enter to attack!");
            scanner.nextLine();
            
            if (!zombies.isEmpty()) {
                Zombie targetZombie = zombies.get(0);
                int totalPlayerDamage = player.getDamage() + player.getInventory().getWeaponDamage();
                targetZombie.setHealth(targetZombie.getHealth() - totalPlayerDamage);
                
                System.out.println("You dealt " + totalPlayerDamage + " damage to zombie!");
                System.out.println("Zombie health: " + targetZombie.getHealth());

                if (targetZombie.getHealth() <= 0) {
                    System.out.println("Zombie defeated!");
                    zombies.remove(0);
                }
            }

            if (zombies.isEmpty()) {
                victory();
                break;
            }

            System.out.println("\n--- Zombies' Turn ---");
            int totalZombieDamage = 0;
            for (Zombie zombie : zombies) {
                totalZombieDamage += zombie.getDamage();
            }

            int finalDamage = Math.max(0, totalZombieDamage - player.getInventory().getArmorDefense());
            player.setHealth(player.getHealth() - finalDamage);
            
            System.out.println("Zombies dealt " + finalDamage + " damage to you!");
            System.out.println("Your health: " + player.getHealth());

            if (player.getHealth() <= 0) {
                defeat();
                break;
            }
        }
    }
    
    private void victory() {
        System.out.println("\n🎉 VICTORY! 🎉");
        System.out.println("You defeated all zombies in the cave!");

        int totalGold = initialZombieCount * 4;
        int oldGold = player.getGold();
        player.setGold(player.getGold() + totalGold);
        
        System.out.println("You defeated " + initialZombieCount + " zombie(s)!");
        System.out.println("You earned " + totalGold + " gold!");
        System.out.println("Gold: " + oldGold + " -> " + player.getGold());

        player.getInventory().setFood(true);
        System.out.println("You found food in the cave!");

        player.setCaveCompleted(true);
        
        zombies.clear();
    }
    
    private void defeat() {
        System.out.println("\n💀 DEFEAT! 💀");
        System.out.println("You have been defeated by the zombies...");
        System.out.println("Game Over!");
        System.exit(0);
    }
    
    public List<Zombie> getZombies() {
        return zombies;
    }
    
    public int getZombieCount() {
        return zombies.size();
    }
}