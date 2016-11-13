package rich.command;

import rich.Player;
import rich.response.Response;

public interface Command {
    Player.Status execute(Player player);

    Player.Status respond(Player player, Response response);
}
