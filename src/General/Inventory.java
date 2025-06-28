package General;

public class Inventory {
    boolean water = false;
    boolean food = false;
    boolean firewood = false;
    String weaponName;
    String armorName;
    int weaponDamage;
    int armorDefense;

    public Inventory() {
        this.water = water;
        this.food = food;
        this.firewood = firewood;
        this.weaponName = weaponName;
        this.armorName = armorName;
        this.weaponDamage = weaponDamage;
        this.armorDefense = armorDefense;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getArmorName() {
        return armorName;
    }

    public void setArmorName(String armorName) {
        this.armorName = armorName;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public int getArmorDefense() {
        return armorDefense;
    }

    public void setArmorDefense(int armorDefense) {
        this.armorDefense = armorDefense;
    }

    @Override
    public String toString() {
        return "Your Inventory :" +
                " \nWater = " + water +
                " \nFood = " + food +
                " \nFirewood = " + firewood +
                " \nWeaponName = " + weaponName +
                " \nArmorName = " + armorName  +
                " \nWeaponDamage = " + weaponDamage +
                " \nArmorDefense = " + armorDefense;
    }
}
