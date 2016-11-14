package rich;

import com.sun.tools.javac.util.Pair;
import rich.place.Place;

public class Mine implements Place {
    private int position;
    private int point;

    public Mine(int position, int point) {
        this.position = position;
        this.point = point;
    }

    public Pair<Player.Status, String> visitedBy(Player player) {
        player.gainPoint(point);
        return Pair.of(Player.Status.TURN_END, "获得" + point + "点");
    }

    public int getPosition() {
        return position;
    }

    public void print() {
        System.out.print("$");
    }
}
