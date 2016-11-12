package rich.place;

import rich.Player;

public class StartingPoint implements Place {
    private int position;

    public StartingPoint(int position) {
        this.position = position;
    }

    public boolean isInputRequired(Player player) {
        return false;
    }

    public int getPosition() {
        return position;
    }
}
