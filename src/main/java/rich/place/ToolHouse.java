package rich.place;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public class ToolHouse implements Place {
    private int position;

    public ToolHouse(int position) {
        this.position = position;
    }

    public Pair<Player.Status, String> visitedBy(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("欢迎光临道具屋， 请选择您所需要的道具：\n");
        sb.append("1. 路障 50点\n");
        sb.append("2. 机器人 30点\n");
        sb.append("3. 炸弹 50点\n");
        int cheapest = Math.min(Math.min(Robot.Price, Barricade.Price), Math.min(Robot.Price, Bomb.Price));
        boolean noBudgetForCheapest = player.getPoint() < cheapest;
        if (noBudgetForCheapest) {
            sb.append("点数不足以购买最便宜物品, 再见");
            return Pair.of(Player.Status.TURN_END, sb.toString());
        } else {
            return Pair.of(Player.Status.WAIT_FOR_RESPONSE, sb.toString());
        }
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("T");
    }
}
