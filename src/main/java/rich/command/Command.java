package rich.command;

import rich.Player;
import rich.response.Response;

public interface Command {
    // TODO: 11/13/16 change command to class | define CommandStrategy interface to be passed to command as parameter

    Player.Status execute(Player player);

    Player.Status respond(Player player, Response response);
}
