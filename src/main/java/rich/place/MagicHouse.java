package rich.place;

import rich.Player;

public class MagicHouse implements Place {
    private int position;

    public MagicHouse(int position) {
        this.position = position;
    }

    public boolean isInputRequired(Player player) {
        return false;
    }

    public int getPosition() {
        return position;
    }
}
