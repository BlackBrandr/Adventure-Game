import General.Cave;
import General.River;
import General.Forest;
import General.SafeHouse;
import General.ToolStore;
import General.Inventory;
import General.Player;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.StartGame();
    }

    public void StartGame() {
        System.out.println("Welcome to the Adventure Game!");
        Scanner input = new Scanner(System.in);

        System.out.println("Press Enter to Start!");
        input.nextLine();

        Inventory inventory = new Inventory();
        Player player = new Player(inventory, "", 0, 0, 0);

        System.out.println("Set your player name:");
        String playerName = input.nextLine();
        player.setName(playerName);

        System.out.println("Welcome " + playerName + "!\n");

        player.SelectCharacter();


        while (true) {
            if (player.getHealth() <= 0) {
                System.out.println("Game Over!");
                break;
            }

            if (checkWinCondition(player)) {
                showWinMessage(player);
                break;
            }

            showMainMenu();
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    showInventory(inventory, input);
                    break;
                    
                case 2:
                    showLocationMenu(player);
                    int locationChoice = input.nextInt();
                    input.nextLine();
                    
                    switch (locationChoice) {
                        case 1:
                            SafeHouse safeHouse = new SafeHouse(player);
                            safeHouse.onLocation();
                            System.out.println("\nPress Enter to continue...");
                            input.nextLine();
                            break;
                        case 2:
                            ToolStore toolStore = new ToolStore(player);
                            toolStore.onLocation();
                            System.out.println("\nPress Enter to continue...");
                            input.nextLine();
                            break;
                        case 3:
                            if (player.isForestCompleted()) {
                                System.out.println("✅ You have already completed the Forest!");
                            } else {
                                System.out.println("\n🌲 Entering the Forest... 🌲");
                                System.out.println("You hear mysterious whispers in the darkness...");
                                
                                Forest forest = new Forest(player);
                                forest.combat();

                                if (player.getHealth() <= 0) {
                                    return;
                                } else {
                                    System.out.println("\nPress Enter to continue...");
                                    input.nextLine();
                                }
                            }
                            break;
                        case 4:
                            if (player.isCaveCompleted()) {
                                System.out.println("✅ You have already completed the Cave!");
                            } else {
                                System.out.println("\n🏔️ Entering the Cave... 🏔️");
                                System.out.println("You feel a chill in the air...");
                                
                                Cave cave = new Cave(player, inventory);
                                cave.combat();

                                if (player.getHealth() <= 0) {
                                    return;
                                } else {
                                    System.out.println("\nPress Enter to continue...");
                                    input.nextLine();
                                }
                            }
                            break;
                        case 5:
                            if (player.isRiverCompleted()) {
                                System.out.println("✅ You have already completed the River!");
                            } else {
                                System.out.println("\n🏞️ Approaching the River... 🏞️");
                                System.out.println("You hear the sound of flowing water and growling...");
                                
                                River river = new River(player);
                                river.combat();

                                if (player.getHealth() <= 0) {
                                    return;
                                } else {
                                    System.out.println("\nPress Enter to continue...");
                                    input.nextLine();
                                }
                            }
                            break;
                            case 6:
                                if (player.isCaveCompleted()) {
                                    System.out.println("✅ You have already completed the Cave!");
                                } else {
                                    System.out.println("\n\uD83C\uDF59 Approaching the Cave... \uD83C\uDF59");
                                    System.out.println("You hear the sound of flowing snakes and fear...");

                                    Cave cave = new Cave(player, inventory);
                                    cave.combat();

                                    if (player.getHealth() <= 0) {
                                        return;
                                    } else {
                                        System.out.println("\nPress Enter to continue...");
                                        input.nextLine();
                                    }
                                }
                                break;

                        default:
                            System.out.println("Invalid choice!");
                    }
                    break;
                    
                case 3:
                    showCharacterInfo(player, input);
                    break;
                    
                case 4:
                    System.out.println("Thanks for playing! Goodbye!");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private boolean checkWinCondition(Player player) {
        Inventory inventory = player.getInventory();
        return inventory.isWater() && inventory.isFood() && inventory.isFirewood();
    }

    private void showWinMessage(Player player) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎉🎉🎉 CONGRATULATIONS! YOU WON THE GAME! 🎉🎉🎉");
        System.out.println("=".repeat(50));
        System.out.println("🏆 " + player.getName() + ", you are a true adventurer! 🏆");
        System.out.println("");
        System.out.println("🎯 You have successfully collected all survival items:");
        System.out.println("💧 Water - Found at the River");
        System.out.println("🍖 Food - Found in the Cave");
        System.out.println("🔥 Firewood - Found in the Forest");
        System.out.println("");
        System.out.println("⚔️ Final Stats:");
        System.out.println("❤️ Health: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("💪 Damage: " + player.getDamage());
        System.out.println("💰 Gold: " + player.getGold());
        
        if (player.getInventory().getWeaponName() != null) {
            System.out.println("⚔️ Weapon: " + player.getInventory().getWeaponName() + 
                             " (+" + player.getInventory().getWeaponDamage() + " damage)");
        }
        
        if (player.getInventory().getArmorName() != null) {
            System.out.println("🛡️ Armor: " + player.getInventory().getArmorName() + 
                             " (+" + player.getInventory().getArmorDefense() + " defense)");
        }
        
        System.out.println("");
        System.out.println("🌟 You have proven yourself as a master adventurer!");
        System.out.println("🏞️ All locations conquered, all items collected!");
        System.out.println("🎮 Thank you for playing the Adventure Game!");
        System.out.println("=".repeat(50));
    }

    private void showMainMenu() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("🎮 ADVENTURE GAME MENU 🎮");
        System.out.println("=".repeat(30));
        System.out.println("1. Check Inventory");
        System.out.println("2. Locations");
        System.out.println("3. Character Information");
        System.out.println("4. Exit Game");
        System.out.println("=".repeat(30));
        System.out.print("Choose an option: ");
    }

    private void showLocationMenu(Player player) {
        System.out.println("\n" + "=".repeat(25));
        System.out.println("🗺️ LOCATIONS 🗺️");
        System.out.println("=".repeat(25));
        System.out.println("1. Safe House 🏠");
        System.out.println("2. Tool Store 🏪");

        if (player.isForestCompleted()) {
            System.out.println("3. Forest ✅");
        } else {
            System.out.println("3. Forest ⚔️");
        }

        if (player.isCaveCompleted()) {
            System.out.println("4. Cave ✅");
        } else {
            System.out.println("4. Cave ⚔️");
        }

        if (player.isRiverCompleted()) {
            System.out.println("5. River ✅");
        } else {
            System.out.println("5. River ⚔️");
        }

        if (player.isCaveCompleted()) {
            System.out.println("6. Cave ✅");
        } else {
            System.out.println("6. Cave ⚔️");
        }
        
        System.out.println("=".repeat(25));
        System.out.print("Choose a location: ");
    }

    private void showInventory(Inventory inventory, Scanner input) {
        while (true) {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("🎒 INVENTORY 🎒");
            System.out.println("=".repeat(35));
            System.out.println(inventory.toString());
            System.out.println("=".repeat(35));
            System.out.println("1. Go back to menu");
            System.out.println("=".repeat(35));
            System.out.print("Choose an option: ");
            
            int choice = input.nextInt();
            input.nextLine();
            
            if (choice == 1) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void showCharacterInfo(Player player, Scanner input) {
        while (true) {
            System.out.println("\n" + "=".repeat(35));
            System.out.println("👤 CHARACTER INFORMATION 👤");
            System.out.println("=".repeat(35));
            System.out.println("Name: " + player.getName());
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println("Damage: " + player.getDamage());
            System.out.println("Gold: " + player.getGold());
            System.out.println("=".repeat(35));
            System.out.println("1. Go back to menu");
            System.out.println("=".repeat(35));
            System.out.print("Choose an option: ");
            
            int choice = input.nextInt();
            input.nextLine();
            
            if (choice == 1) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}