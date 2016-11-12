package rich.place;

import rich.Player;

public class ToolHouse implements Place {
    private int position;

    public ToolHouse(int position) {
        this.position = position;
    }

    public boolean isInputRequired(Player player) {
        return true;
    }

    public int getPosition() {
        return position;
    }

}
