package rich.place;

import rich.Player;

public class GiftHouse implements Place {
    private int position;

    public GiftHouse(int position) {
        this.position = position;
    }

    public boolean isInputRequired(Player player) {
        return true;
    }

    public int getPosition() {
        return position;
    }
}
