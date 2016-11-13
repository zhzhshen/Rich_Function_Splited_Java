package rich.command;

import rich.Player;
import rich.response.Response;

public class SellCommand implements Command {
    private int position;

    public SellCommand(int position) {
        this.position = position;
    }

    public Player.Status execute(Player player) {
        player.sell(position);
        return Player.Status.WAIT_FOR_COMMAND;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
