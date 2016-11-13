package rich.place;

import rich.Player;

public class Hospital implements Place {
    private int position;

    public Hospital(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        return Player.Status.TURN_END;
    }

    public int getPosition() {
        return position;
    }
}
