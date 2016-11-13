package rich.command;

import rich.Player;
import rich.response.Response;

public class HelpCommand implements Command{
    @Override
    public Player.Status execute(Player player) {
        return null;
    }

    @Override
    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HelpCommand;
    }
}
