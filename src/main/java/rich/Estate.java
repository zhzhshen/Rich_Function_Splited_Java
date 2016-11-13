package rich;

public class Estate implements Place{
    private static final int MAX_LEVEL = 3;
    private Player owner;
    private int level;
    private double price;

    public Estate(double price) {
        this.level = 0;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void sellTo(Player player) {
        owner = player;
    }

    public void build() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    public boolean isMaxLevel() {
        return level == MAX_LEVEL;
    }

    public void charge(Player player) {
        if (owner.isInHospital() || owner.isInPrison() || player.hasEvisu()) {
            return;
        }
        double charge = 0.5 * price * Math.pow(2, level);
        player.reduceMoney(charge);
        owner.gainMoney(charge);
    }
}
