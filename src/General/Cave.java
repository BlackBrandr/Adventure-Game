package General;

import Enemies.Snake;

import java.util.*;

import General.Inventory;

public class Cave extends BattleLocation {
    private List<Snake> snakes;
    private General.Player player;
    private Inventory invetory;
    private int initialSnakeCount;

    int oldGold;
    String oldWeapon;
    String oldArmor;

    
    public Cave(General.Player player, Inventory inventory) {
        super("Cave");
        this.player = player;
        this.snakes = new ArrayList<>();
        this.invetory = inventory;

        oldGold = player.getGold();

        oldWeapon = invetory.getWeaponName();
        oldArmor = invetory.getArmorName();

        createSnakes();
    }
    
    public int enemyNumber() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }
    
    private void createSnakes() {
        int snakeCount = enemyNumber();
        this.initialSnakeCount = snakeCount;
        
        for (int i = 0; i < snakeCount; i++) {
            Snake snake = new Snake(4, 12, 3, 0);
            snakes.add(snake);
        }
        
        System.out.println(snakeCount + " snake(s) spawned in the cave!");
    }
    
    @Override
    public void playerAttack() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- Player's Turn ---");
        System.out.println("Your health: " + player.getHealth());
        System.out.println("Remaining snakes: " + snakes.size());
        System.out.println("Press Enter to attack!");
        scanner.nextLine();

        if (!snakes.isEmpty()) {
            Snake targetSnake = snakes.get(0);
            int totalPlayerDamage = player.getDamage() + player.getInventory().getWeaponDamage();
            targetSnake.setHealth(targetSnake.getHealth() - totalPlayerDamage);

            System.out.println("You dealt " + totalPlayerDamage + " damage to snake!");
            System.out.println("Snake health: " + targetSnake.getHealth());

            if (targetSnake.getHealth() <= 0) {
                System.out.println("Snake defeated!");
                targetSnake.dropLoot(player);
                snakes.remove(0);
            }
        }
    }

    @Override
    public void enemyAttack() {
        System.out.println("\n--- Snakes Turn ---");
        int totalSnakeDamage = 0;
        for (Snake snake : snakes) {
            totalSnakeDamage += snake.randomDamage();
        }

        int finalDamage = Math.max(0, totalSnakeDamage - player.getInventory().getArmorDefense());
        player.setHealth(player.getHealth() - finalDamage);

        System.out.println("Snakes dealt " + finalDamage + " damage to you!");
        System.out.println("Your health: " + player.getHealth());

        if (player.getHealth() <= 0) {
            defeat();
        }
    }

    @Override
    public void combat() {
        Random random = new Random();

        boolean playerStartsFirst = random.nextBoolean();
        
        System.out.println(playerStartsFirst ? "You get the first move!" : "The snakes get the first move!");
        
        while (player.getHealth() > 0 && !snakes.isEmpty()) {
            if (playerStartsFirst) {
                playerAttack();
                if (!snakes.isEmpty()) {
                    enemyAttack();
                }
            } else {
                enemyAttack();
                if (player.getHealth() > 0) {
                    playerAttack();
                }
            }
            
            if (snakes.isEmpty()) {
                victory();
                break;
            }
        }
    }
    
    private void victory() {
        System.out.println("\n🎉 VICTORY! 🎉");
        System.out.println("You defeated all snakes in the cave!");

        int earnedGold = player.getGold() - oldGold;
        
        System.out.println("You defeated " + initialSnakeCount + " snake(s)!");
        System.out.println("You earned " + earnedGold + " gold!");
        System.out.println("Gold: " + player.getGold());

        if (!Objects.equals(oldArmor, invetory.getArmorName())) {
            System.out.println("You dropped :" + invetory.getArmorName());
        }

        if (!Objects.equals(oldWeapon, invetory.getWeaponName())){
            System.out.println("You dropped :" + invetory.getWeaponName());
        }

        player.setCaveCompleted(true);

        snakes.clear();
    }
    
    private void defeat() {
        System.out.println("\n💀 DEFEAT! 💀");
        System.out.println("You have been defeated by the snakes...");
        System.out.println("Game Over!");
        System.exit(0);
    }
    
    public List<Snake> getSnakes() {
        return snakes;
    }
    
    public int getSnakeCount() {
        return snakes.size();
    }
}