package rich.command;

import com.sun.tools.javac.util.Pair;
import rich.Dice;
import rich.Player;
import rich.response.Response;
import rich.place.Place;

public class RollCommand implements Command {
    private Dice dice;

    public RollCommand(Dice dice) {
        this.dice = dice;
    }

    public Pair<Player.Status, String> execute(Player player) {
        Place newPlace = player.getMap().move(player, dice.roll());
        player.moveToPlace(newPlace);
        return player.getCurrentPlace().visitedBy(player);
    }

    public Pair<Player.Status, String> respond(Player player, Response response) {
        return response.execute(player);
    }
}
