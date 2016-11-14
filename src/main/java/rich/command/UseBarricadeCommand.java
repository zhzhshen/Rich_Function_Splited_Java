package rich.command;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.item.Barricade;
import rich.response.Response;

public class UseBarricadeCommand implements Command {
    private Barricade barricade = new Barricade();

    private int steps;

    public UseBarricadeCommand(int steps) {
        this.steps = steps;
    }

    public Pair<Player.Status, String> execute(Player player) {
        return Pair.of(Player.Status.WAIT_FOR_COMMAND, player.use(barricade, steps));
    }

    public Pair<Player.Status, String> respond(Player player, Response response) {
        return response.execute(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UseBarricadeCommand that = (UseBarricadeCommand) o;

        return steps == that.steps;
    }

    @Override
    public int hashCode() {
        return steps;
    }
}
