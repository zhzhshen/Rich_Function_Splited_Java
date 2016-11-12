public class Land implements Place, Buyable {
    private static final int MAX_LEVEL = 3;
    private Player owner;
    private int unitPrice;
    private int level;

    public Land() {
    }

    public Land(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Land(int unitPrice, int level) {
        this(unitPrice);
        this.level = level;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public boolean isInputRequired() {
        return isMaxLevel() ? false : true;
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
        } else if (owner.equals(player)){
            builtBy(player);
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
