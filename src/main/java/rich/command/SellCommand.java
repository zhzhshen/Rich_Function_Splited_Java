package rich.command;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.response.Response;

public class SellCommand implements Command {
    private int position;

    public SellCommand(int position) {
        this.position = position;
    }

    public Pair<Player.Status, String> execute(Player player) {
        player.sell(position);
        return Pair.of(Player.Status.WAIT_FOR_COMMAND, null);
    }

    public Pair<Player.Status, String> respond(Player player, Response response) {
        return response.execute(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellCommand that = (SellCommand) o;

        return position == that.position;

    }

    @Override
    public int hashCode() {
        return position;
    }
}
