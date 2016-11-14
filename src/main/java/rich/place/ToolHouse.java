package rich.place;

import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public class ToolHouse implements Place {
    private int position;

    public ToolHouse(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        System.out.println("欢迎光临道具屋， 请选择您所需要的道具：");
        System.out.println("1. 路障 50点");
        System.out.println("2. 机器人 30点");
        System.out.println("3. 炸弹 50点");
        int cheapest = Math.min(Math.min(Robot.Price, Barricade.Price), Math.min(Robot.Price, Bomb.Price));
        boolean noBudgetForCheapest = player.getPoint() < cheapest;
        if (noBudgetForCheapest) {
            System.out.println("点数不足以购买最便宜物品, 再见");
            return Player.Status.TURN_END;
        } else {
            return Player.Status.WAIT_FOR_RESPONSE;
        }
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("T");
    }
}
