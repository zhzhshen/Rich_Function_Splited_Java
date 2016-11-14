package rich.place;

import rich.Player;

public class GiftHouse implements Place{
    private int position;

    public GiftHouse(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        System.out.println("欢迎光临礼品屋，请选择一件您 喜欢的礼品：");
        System.out.println("1. 奖金 2000元");
        System.out.println("2. 点数 200点");
        System.out.println("3. 福神 5回合");
        return Player.Status.WAIT_FOR_RESPONSE;
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("G");
    }
}
