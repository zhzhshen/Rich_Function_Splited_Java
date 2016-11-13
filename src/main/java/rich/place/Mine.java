package rich.place;

import rich.Player;

public class Mine implements Place {
    private int position;
    private final int point;

    public Mine(int position, int point) {
        this.position = position;
        this.point = point;
    }

    public boolean isInputRequired(Player player) {
        return false;
    }

    public int getPosition() {
        return position;
    }

    public int getPoint() {
        return point;
    }

    public void visitBy(Player player) {
        player.gainPoint(point);
    }
}
