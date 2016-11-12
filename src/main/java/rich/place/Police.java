package rich.place;

import rich.Player;

public class Police implements Place {
    private int position;

    public Police(int position) {
        this.position = position;
    }

    public boolean isInputRequired(Player player) {
        return false;
    }

    public int getPosition() {
        return position;
    }

}
