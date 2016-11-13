package rich;

public class Estate implements Place {
    private static final int MAX_LEVEL = 3;
    private Player owner;
    private int level;
    private double price;
    private int position;

    public Estate(int position, double price) {
        this.position = position;
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

    private void charge(Player player) {
        if (owner.isInHospital() || owner.isInPrison() || player.hasEvisu()) {
            return;
        }
        double charge = 0.5 * price * Math.pow(2, level);
        player.reduceMoney(charge);
        owner.gainMoney(charge);
    }

    public Player.Status visitedBy(Player player) {
        if (owner == null || owner.equals(player)) {
            return Player.Status.WAIT_FOR_RESPONSE;
        } else {
            charge(player);
            return Player.Status.TURN_END;
        }
    }

    public int getPosition() {
        return position;
    }

    public double sell() {
        double charge = price * Math.pow(2, level);
        owner = null;
        level = 0;
        return charge;
    }
}
