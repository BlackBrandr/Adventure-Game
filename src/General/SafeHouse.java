package General;

public class SafeHouse extends NormalLocation {
    private Player player;

    public SafeHouse(Player player) {
        super("Safe House");
        this.player = player;
    }

    @Override
    public boolean onLocation() {
        System.out.println("\n🏠 Welcome to the Safe House! 🏠");
        System.out.println("You feel safe and peaceful here...");
        
        int oldHealth = player.getHealth();
        player.restoreFullHealth();
        
        if (oldHealth < player.getMaxHealth()) {
            System.out.println("✨ Your health has been fully restored! ✨");
            System.out.println("Health: " + oldHealth + " → " + player.getHealth());
        } else {
            System.out.println("Your health is already at maximum!");
        }
        
        System.out.println("You rest peacefully in the safety of your home.");
        return true;
    }
}