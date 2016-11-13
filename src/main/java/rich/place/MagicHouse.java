package rich.place;

import rich.Player;

public class MagicHouse implements Place {
    private int position;

    public MagicHouse(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        return Player.Status.TURN_END;
    }

    public int getPosition() {
        return position;
    }
}
