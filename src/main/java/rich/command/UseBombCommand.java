package rich.command;

import rich.Player;
import rich.response.Response;
import rich.item.Bomb;

public class UseBombCommand implements Command {
    private int steps;

    public UseBombCommand(int steps) {
        this.steps = steps;
    }

    public Player.Status execute(Player player) {
        player.use(new Bomb(), steps);
        return Player.Status.WAIT_FOR_COMMAND;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UseBombCommand that = (UseBombCommand) o;

        return steps == that.steps;

    }

    @Override
    public int hashCode() {
        return steps;
    }
}
