package General;

import Enemies.Vampire;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Forest extends BattleLocation {
    private List<Vampire> vampires;
    private General.Player player;
    private int initialVampireCount;
    
    public Forest(General.Player player) {
        super("Forest");
        this.player = player;
        this.vampires = new ArrayList<>();
        createVampires();
    }
    
    public int enemyNumber() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
    
    private void createVampires() {
        int vampireCount = enemyNumber();
        this.initialVampireCount = vampireCount;
        
        for (int i = 0; i < vampireCount; i++) {
            Vampire vampire = new Vampire(3, 14, 4, 12);
            vampires.add(vampire);
        }
        
        System.out.println(vampireCount + " vampire(s) spawned in the forest!");
    }
    
    @Override
    public void playerAttack() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Player's Turn ---");
        System.out.println("Your health: " + player.getHealth());
        System.out.println("Remaining vampires: " + vampires.size());
        System.out.println("Press Enter to attack!");
        scanner.nextLine();

        if (!vampires.isEmpty()) {
            Vampire targetVampire = vampires.get(0);
            int totalPlayerDamage = player.getDamage() + player.getInventory().getWeaponDamage();
            targetVampire.setHealth(targetVampire.getHealth() - totalPlayerDamage);

            System.out.println("You dealt " + totalPlayerDamage + " damage to vampire!");
            System.out.println("Vampire health: " + targetVampire.getHealth());

            if (targetVampire.getHealth() <= 0) {
                System.out.println("Vampire defeated!");
                vampires.remove(0);
            }
        }
    }

    @Override
    public void enemyAttack() {
        System.out.println("\n--- Vampires' Turn ---");
        int totalVampireDamage = 0;
        for (Vampire vampire : vampires) {
            totalVampireDamage += vampire.getDamage();
        }

        int finalDamage = Math.max(0, totalVampireDamage - player.getInventory().getArmorDefense());
        player.setHealth(player.getHealth() - finalDamage);

        System.out.println("Vampires dealt " + finalDamage + " damage to you!");
        System.out.println("Your health: " + player.getHealth());

        if (player.getHealth() <= 0) {
            defeat();
        }
    }

    @Override
    public void combat() {
        Random random = new Random();
        boolean playerStartsFirst = random.nextBoolean();
        
        System.out.println(playerStartsFirst ? "You get the first move!" : "The vampires get the first move!");
        
        while (player.getHealth() > 0 && !vampires.isEmpty()) {
            if (playerStartsFirst) {
                playerAttack();
                if (!vampires.isEmpty()) {
                    enemyAttack();
                }
            } else {
                enemyAttack();
                if (player.getHealth() > 0) {
                    playerAttack();
                }
            }
            
            if (vampires.isEmpty()) {
                victory();
                break;
            }
        }
    }
    
    private void victory() {
        System.out.println("\n🎉 VICTORY! 🎉");
        System.out.println("You defeated all vampires in the forest!");

        int totalGold = initialVampireCount * 12;
        int oldGold = player.getGold();
        player.setGold(player.getGold() + totalGold);
        
        System.out.println("You defeated " + initialVampireCount + " vampire(s)!");
        System.out.println("You earned " + totalGold + " gold!");
        System.out.println("Gold: " + oldGold + " -> " + player.getGold());

        player.getInventory().setFirewood(true);
        System.out.println("You found firewood in the forest!");

        player.setForestCompleted(true);
        
        vampires.clear();
    }
    
    private void defeat() {
        System.out.println("\n💀 DEFEAT! 💀");
        System.out.println("You have been defeated by the vampires...");
        System.out.println("Game Over!");
        System.exit(0);
    }
    
    public List<Vampire> getVampires() {
        return vampires;
    }
    
    public int getVampireCount() {
        return vampires.size();
    }
}