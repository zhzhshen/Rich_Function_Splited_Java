package rich.place;

import com.sun.tools.javac.util.Pair;
import rich.Player;

public class GiftHouse implements Place{
    private int position;

    public GiftHouse(int position) {
        this.position = position;
    }

    public Pair<Player.Status, String> visitedBy(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("欢迎光临礼品屋，请选择一件您 喜欢的礼品：\n");
        sb.append("1. 奖金 2000元");
        sb.append("2. 点数 200点");
        sb.append("3. 福神 5回合");
        return Pair.of(Player.Status.WAIT_FOR_RESPONSE, sb.toString());
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("G");
    }
}
