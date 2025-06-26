import Locations.Cave;
import Locations.River;
import Locations.Forest;
import Locations.SafeHouse;
import Locations.Inventory;
import Locations.Player;

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
                            System.out.println("Tool Store - Coming soon!");
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
                                
                                Cave cave = new Cave(player);
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
        System.out.println("2. Tool Store");
        
        // Forest durumu kontrolü
        if (player.isForestCompleted()) {
            System.out.println("3. Forest ✅");
        } else {
            System.out.println("3. Forest ⚔️");
        }
        
        // Cave durumu kontrolü
        if (player.isCaveCompleted()) {
            System.out.println("4. Cave ✅");
        } else {
            System.out.println("4. Cave ⚔️");
        }
        
        // River durumu kontrolü
        if (player.isRiverCompleted()) {
            System.out.println("5. River ✅");
        } else {
            System.out.println("5. River ⚔️");
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