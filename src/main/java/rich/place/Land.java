package rich.place;

import rich.Player;

public class Land implements Place {
    private static final int MAX_LEVEL = 3;
    private int position;
    private Player owner;
    private double unitPrice;
    private int level;

    public Land() {
    }

    public Land(int position, double unitPrice, int level) {
        this.position = position;
        this.unitPrice = unitPrice;
        this.level = level;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
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

    public void charge(Player player) {
        double charge = (unitPrice / 2) * Math.pow(2, level);
        if (player.reduceMoney(charge)) {
            owner.gainMoney(charge);
        }
    }

    public void builtBy(Player player) {
        if (!isMaxLevel() && player.reduceMoney(unitPrice)) {
            level += 1;
        }
    }

    public void sellTo(Player player) {
        if (player.reduceMoney(unitPrice)) {
            owner = player;
            player.gainLand(this);
        }
    }

    public double getPrice() {
        return unitPrice;
    }

    public int getPosition() {
        return position;
    }
}
