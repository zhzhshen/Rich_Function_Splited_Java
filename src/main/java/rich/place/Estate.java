package rich.place;

import com.sun.tools.javac.util.Pair;
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

    private String charge(Player player) {
        if (owner.isInHospital()) {
            return owner.getName() + "在医院, 免付过路费";
        } else if (owner.isInPrison()) {
            return owner.getName() + "在监狱, 免付过路费";
        } else if (player.hasEvisu()) {
            return "福神保佑,免付过路费~";
        }
        double charge = 0.5 * price * Math.pow(2, level);
        if (player.reduceMoney(charge)) {
            owner.gainMoney(charge);
            String message = "向" + owner.getName() + "付过路费" + charge + "元";
            return message;
        } else {
            owner.gainMoney(player.getBalance());
            String message = "余额不足以支付" + owner.getName() + "过路费" + charge + "元, 扑街!";
            player.remove();
            return message;
        }
    }

    public Pair<Player.Status, String> visitedBy(Player player) {
        String message;
        if (owner == null || owner.equals(player)) {
            message = "是否" + (owner == null ? "购买" : "升级") + "该处土地，" + price + "元（Y/N）?";
            return Pair.of(Player.Status.WAIT_FOR_RESPONSE, message);
        } else {
            message = charge(player);
            return Pair.of(Player.Status.TURN_END, message);
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
