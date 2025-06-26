package Locations;

import java.util.Scanner;

public class ToolStore extends NormalLocation {
    private Player player;

    public ToolStore(Player player) {
        super("Tool Store");
        this.player = player;
    }

    @Override
    public boolean onLocation() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            showStore();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    showWeapons();
                    buyWeapon(scanner);
                    break;
                case 2:
                    showArmors();
                    buyArmor(scanner);
                    break;
                case 3:
                    System.out.println("Thanks for visiting! Come back soon!");
                    return true;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void showStore() {
        System.out.println("\n" + "=".repeat(35));
        System.out.println("🏪 WELCOME TO TOOL STORE 🏪");
        System.out.println("=".repeat(35));
        System.out.println("💰 Your Gold: " + player.getGold());
        System.out.println("=".repeat(35));
        System.out.println("1. Weapons ⚔️");
        System.out.println("2. Armors 🛡️");
        System.out.println("3. Exit Store 🚪");
        System.out.println("=".repeat(35));
    }

    private void showWeapons() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("⚔️ WEAPONS ⚔️");
        System.out.println("=".repeat(40));
        System.out.println("💰 Your Gold: " + player.getGold());
        System.out.println("=".repeat(40));
        System.out.println("1. Pistol - Damage: 2 - Price: 25 Gold");
        System.out.println("2. Sword - Damage: 3 - Price: 35 Gold");
        System.out.println("3. Rifle - Damage: 7 - Price: 45 Gold");
        System.out.println("4. Go Back");
        System.out.println("=".repeat(40));
    }

    private void showArmors() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🛡️ ARMORS 🛡️");
        System.out.println("=".repeat(40));
        System.out.println("💰 Your Gold: " + player.getGold());
        System.out.println("=".repeat(40));
        System.out.println("1. Light Armor - Defense: 1 - Price: 15 Gold");
        System.out.println("2. Normal Armor - Defense: 3 - Price: 25 Gold");
        System.out.println("3. Heavy Armor - Defense: 5 - Price: 40 Gold");
        System.out.println("4. Go Back");
        System.out.println("=".repeat(40));
    }

    private void buyWeapon(Scanner scanner) {
        System.out.print("Choose a weapon: ");
        int weaponChoice = scanner.nextInt();
        
        String weaponName = "";
        int weaponDamage = 0;
        int weaponPrice = 0;
        
        switch (weaponChoice) {
            case 1:
                weaponName = "Pistol";
                weaponDamage = 2;
                weaponPrice = 25;
                break;
            case 2:
                weaponName = "Sword";
                weaponDamage = 3;
                weaponPrice = 35;
                break;
            case 3:
                weaponName = "Rifle";
                weaponDamage = 7;
                weaponPrice = 45;
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        if (player.getGold() >= weaponPrice) {
            if (player.getInventory().getWeaponName() != null) {
                System.out.println("Your old weapon (" + player.getInventory().getWeaponName() + ") has been replaced!");
            }

            player.setGold(player.getGold() - weaponPrice);
            player.getInventory().setWeaponName(weaponName);
            player.getInventory().setWeaponDamage(weaponDamage);
            
            System.out.println("✅ " + weaponName + " purchased successfully!");
            System.out.println("💰 Gold: " + (player.getGold() + weaponPrice) + " → " + player.getGold());
            System.out.println("⚔️ Weapon Damage: " + weaponDamage);
        } else {
            System.out.println("❌ Insufficient gold! You need " + weaponPrice + " gold but you have " + player.getGold() + " gold.");
        }
    }

    private void buyArmor(Scanner scanner) {
        System.out.print("Choose an armor: ");
        int armorChoice = scanner.nextInt();
        
        String armorName = "";
        int armorDefense = 0;
        int armorPrice = 0;
        
        switch (armorChoice) {
            case 1:
                armorName = "Light Armor";
                armorDefense = 1;
                armorPrice = 15;
                break;
            case 2:
                armorName = "Normal Armor";
                armorDefense = 3;
                armorPrice = 25;
                break;
            case 3:
                armorName = "Heavy Armor";
                armorDefense = 5;
                armorPrice = 40;
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        if (player.getGold() >= armorPrice) {
            if (player.getInventory().getArmorName() != null) {
                System.out.println("Your old armor (" + player.getInventory().getArmorName() + ") has been replaced!");
            }

            player.setGold(player.getGold() - armorPrice);
            player.getInventory().setArmorName(armorName);
            player.getInventory().setArmorDefense(armorDefense);
            
            System.out.println("✅ " + armorName + " purchased successfully!");
            System.out.println("💰 Gold: " + (player.getGold() + armorPrice) + " → " + player.getGold());
            System.out.println("🛡️ Armor Defense: " + armorDefense);
        } else {
            System.out.println("❌ Insufficient gold! You need " + armorPrice + " gold but you have " + player.getGold() + " gold.");
        }
    }
}