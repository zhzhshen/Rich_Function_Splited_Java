package rich.place;

import rich.Player;

public class Hospital implements Place {
    private int position;

    public Hospital(int position) {
        this.position = position;
    }

    public boolean isInputRequired(Player player) {
        return false;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void visitBy(Player player) {

    }

}
