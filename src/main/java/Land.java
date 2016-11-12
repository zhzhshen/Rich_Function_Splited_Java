public class Land implements Place, Buyable {
    private static final int MAX_LEVEL = 3;
    private Player owner;
    private double unitPrice;
    private int level;

    public Land() {
    }

    public Land(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Land(double unitPrice, int level) {
        this(unitPrice);
        this.level = level;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public boolean isInputRequired(Player player) {
        if (owner == null) {
            return true;
        } else if (owner.equals(player)) {
            return isMaxLevel() ? false : true;
        } else {
            return false;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isMaxLevel() {
        return MAX_LEVEL == level;
    }

    public void action(Player player) {
        if (owner == null) {
            sellTo(player);
        } else if (owner.equals(player)) {
            builtBy(player);
        } else {
            if (!owner.isInHospital()
                    && !owner.isInPrison()
                    && !player.hasEvisu()) {
                charge(player);
            }
        }
    }

    private void charge(Player player) {
        double charge = (unitPrice / 2) * Math.pow(2, level);
        if (player.reduceMoney(charge)) {
            owner.gainMoney(charge);
        }
    }

    private void builtBy(Player player) {
        if (!isMaxLevel() && player.reduceMoney(unitPrice)) {
            level += 1;
        }
    }

    private void sellTo(Player player) {
        if (player.reduceMoney(unitPrice)) {
            owner = player;
        }
    }
}
