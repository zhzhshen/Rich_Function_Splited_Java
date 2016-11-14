package rich.place;

import com.sun.tools.javac.util.Pair;
import rich.Player;

public class MagicHouse implements Place {
    private int position;

    public MagicHouse(int position) {
        this.position = position;
    }

    public Pair<Player.Status, String> visitedBy(Player player) {
        return Pair.of(Player.Status.TURN_END, "");
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("M");
    }
}
