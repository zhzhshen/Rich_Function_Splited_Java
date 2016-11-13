package rich.place;

import rich.Player;

public class Police implements Place {
    private int position;

    public Police(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        player.prisoned();
        return Player.Status.TURN_END;
    }

    public int getPosition() {
        return position;
    }
}
