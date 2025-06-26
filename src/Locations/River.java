package Locations;

import Enemies.Bear;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class River extends BattleLocation {
    private List<Bear> bears;
    private Locations.Player player;
    private int initialBearCount;
    
    public River(Locations.Player player) {
        super("River");
        this.player = player;
        this.bears = new ArrayList<>();
        createBears();
    }
    
    public int enemyNumber() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
    
    private void createBears() {
        int bearCount = enemyNumber();
        this.initialBearCount = bearCount;
        
        for (int i = 0; i < bearCount; i++) {
            Bear bear = new Bear(2, 20, 7, 12);
            bears.add(bear);
        }
        
        System.out.println(bearCount + " bear(s) spawned at the river!");
    }
    
    @Override
    public void combat() {
        Scanner scanner = new Scanner(System.in);
        
        while (player.getHealth() > 0 && !bears.isEmpty()) {

            System.out.println("\n--- Player's Turn ---");
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Remaining bears: " + bears.size());
            System.out.println("Press Enter to attack!");
            scanner.nextLine();
            
            if (!bears.isEmpty()) {
                Bear targetBear = bears.get(0);
                int totalPlayerDamage = player.getDamage() + player.getInventory().getWeaponDamage();
                targetBear.setHealth(targetBear.getHealth() - totalPlayerDamage);
                
                System.out.println("You dealt " + totalPlayerDamage + " damage to bear!");
                System.out.println("Bear health: " + targetBear.getHealth());

                if (targetBear.getHealth() <= 0) {
                    System.out.println("Bear defeated!");
                    bears.remove(0);
                }
            }

            if (bears.isEmpty()) {
                victory();
                break;
            }

            System.out.println("\n--- Bears' Turn ---");
            int totalBearDamage = 0;
            for (Bear bear : bears) {
                totalBearDamage += bear.getDamage();
            }

            int finalDamage = Math.max(0, totalBearDamage - player.getInventory().getArmorDefense());
            player.setHealth(player.getHealth() - finalDamage);
            
            System.out.println("Bears dealt " + finalDamage + " damage to you!");
            System.out.println("Your health: " + player.getHealth());

            if (player.getHealth() <= 0) {
                defeat();
                break;
            }
        }
    }
    
    private void victory() {
        System.out.println("\n🎉 VICTORY! 🎉");
        System.out.println("You defeated all bears at the river!");

        int totalGold = initialBearCount * 12;
        int oldGold = player.getGold();
        player.setGold(player.getGold() + totalGold);
        
        System.out.println("You defeated " + initialBearCount + " bear(s)!");
        System.out.println("You earned " + totalGold + " gold!");
        System.out.println("Gold: " + oldGold + " -> " + player.getGold());

        player.getInventory().setWater(true);
        System.out.println("You found water at the river!");

        player.setRiverCompleted(true);
        
        bears.clear();
    }
    
    private void defeat() {
        System.out.println("\n💀 DEFEAT! 💀");
        System.out.println("You have been defeated by the bears...");
        System.out.println("Game Over!");
        System.exit(0);
    }
    
    public List<Bear> getBears() {
        return bears;
    }
    
    public int getBearCount() {
        return bears.size();
    }
}