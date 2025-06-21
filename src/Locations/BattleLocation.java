package Locations;

public abstract class BattleLocation extends Location {
    public BattleLocation(String name) {
        super(name);
    }

    @Override
    public boolean onLocation() {
        return super.onLocation();
    }

    public abstract void combat();

}
