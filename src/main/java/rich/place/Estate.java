package rich.place;

import rich.io.Color;
import rich.Player;

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
        if (player.reduceMoney(charge)) {
            owner.gainMoney(charge);
            System.out.println("向" + owner.getName() + "付过路费" + charge + "元");
        } else {
            owner.gainMoney(player.getBalance());
            System.out.println("余额不足以支付" + owner.getName() + "过路费" + charge + "元, 扑街!");
            player.remove();
        }
    }

    public Player.Status visitedBy(Player player) {
        if (owner == null || owner.equals(player)) {
            System.out.println("是否" + (owner == null ? "购买" : "升级") + "该处土地，" + price + "元（Y/N）?");
            return Player.Status.WAIT_FOR_RESPONSE;
        } else {
            charge(player);
            return Player.Status.TURN_END;
        }
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        if (owner != null) {
            System.out.print(owner.getColor() + String.valueOf(level) + Color.ANSI_RESET);
        } else {
            System.out.print(String.valueOf(level));
        }
    }

    public double sell() {
        double charge = price * Math.pow(2, level);
        owner = null;
        level = 0;
        return charge;
    }
}
