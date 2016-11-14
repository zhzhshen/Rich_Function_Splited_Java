package rich.place;

import com.sun.tools.javac.util.Pair;
import rich.Player;

public class Police implements Place {
    private int position;

    public Police(int position) {
        this.position = position;
    }

    public Pair<Player.Status, String> visitedBy(Player player) {
        player.prisoned();
        return Pair.of(Player.Status.TURN_END, "走到警局,被扣留两回合");
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("P");
    }
}
