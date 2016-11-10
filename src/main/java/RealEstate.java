public class RealEstate {
    public static final int MAX_LEVEL = 3;
    private Player owner;
    private final int price;
    private int level;

    public RealEstate(int price) {
        this.price = price;
        this.level = 0;
    }

    public RealEstate(int price, int level) {
        this.price = price;
        this.level = level;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public int getLevel() {
        return level;
    }

    public boolean build() {
        if (level == MAX_LEVEL) {
            return false;
        }
        level++;
        return true;
    }
}
