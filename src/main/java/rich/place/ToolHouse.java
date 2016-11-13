package rich.place;

import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public class ToolHouse implements Place {
    private int position;

    public ToolHouse(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        int cheapest = Math.min(Math.min(Robot.Price, Barricade.Price), Math.min(Robot.Price, Bomb.Price));
        return player.getPoint() < cheapest ? Player.Status.TURN_END: Player.Status.WAIT_FOR_RESPONSE;
    }

    public int getPosition() {
        return position;
    }
}
