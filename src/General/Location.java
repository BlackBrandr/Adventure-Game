package General;


public abstract class Location {
    String name;
    Player player;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean onLocation(){
        return true;
    }
}

