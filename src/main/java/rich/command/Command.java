package rich.command;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.response.Response;

public interface Command {
    // TODO: 11/13/16 change command to class | define CommandStrategy interface to be passed to command as parameter

    Pair<Player.Status, String> execute(Player player);

    Pair<Player.Status, String> respond(Player player, Response response);
}
