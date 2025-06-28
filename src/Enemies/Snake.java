package Enemies;

import java.util.Random;

public class Snake extends Enemy{
    public Snake(int id, int health, int damage, int gold) {
        super(id, health, damage, gold);
    }

    public int randomDamage(){
        Random random = new Random();
        return random.nextInt(4) + 3; // Returns 3-6
    }

    public void dropLoot(General.Player player){
        Random random = new Random();
        int chance = random.nextInt(100) + 1; // 1-100

        if (chance <= 45) {
            System.out.println("The snake dropped nothing.");
            return;
        }

        chance = random.nextInt(100) + 1;

        if ( 45 < chance && chance <= 70) {
            int goldChance = random.nextInt(100) + 1;
            if (goldChance <= 50) {
                player.setGold(player.getGold() + 1);
                System.out.println("Snake dropped 1 gold! Total: " + player.getGold());
            } else if ( 50 < goldChance && goldChance <= 80) {
                player.setGold(player.getGold() + 5);
                System.out.println("Snake dropped 5 gold! Total: " + player.getGold());
            } else {
                player.setGold(player.getGold() + 10);
                System.out.println("Snake dropped 10 gold! Total: " + player.getGold());
            }
            return;
        }

        if ( 70 < chance && chance <= 85) {
            int weaponChance = random.nextInt(100) + 1;
            if (weaponChance <= 50) {
                player.getInventory().setWeaponName("Pistol");
                player.getInventory().setWeaponDamage(2);
                System.out.println("Snake dropped a Pistol! (+2 damage)");
            } else if ( 50 < weaponChance && weaponChance <= 80) {
                player.getInventory().setWeaponName("Sword");
                player.getInventory().setWeaponDamage(3);
                System.out.println("Snake dropped a Sword! (+3 damage)");
            } else {
                player.getInventory().setWeaponName("Rifle");
                player.getInventory().setWeaponDamage(7);
                System.out.println("Snake dropped a Rifle! (+7 damage)");
            }
            return;
        }

        if ( 85 < chance) {
            int armorChance = random.nextInt(100) + 1;
            if (armorChance <= 50) {
                player.getInventory().setArmorName("Light Armor");
                player.getInventory().setArmorDefense(1);
                System.out.println("Snake dropped Light Armor! (+1 defense)");
            } else if (50 < armorChance && armorChance <= 80) {
                player.getInventory().setArmorName("Normal Armor");
                player.getInventory().setArmorDefense(3);
                System.out.println("Snake dropped Normal Armor! (+3 defense)");
            } else {
                player.getInventory().setArmorName("Heavy Armor");
                player.getInventory().setArmorDefense(5);
                System.out.println("Snake dropped Heavy Armor! (+5 defense)");
            }
        }

    }
}
