package rich.place;

import rich.Player;

public class GiftHouse implements Place{
    private int position;

    public GiftHouse(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        return Player.Status.WAIT_FOR_RESPONSE;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
