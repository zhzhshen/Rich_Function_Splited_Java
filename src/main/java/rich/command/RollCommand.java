package rich.command;

import rich.Dice;
import rich.Player;
import rich.response.Response;
import rich.place.Place;

public class RollCommand implements Command {
    private Dice dice;

    public RollCommand(Dice dice) {
        this.dice = dice;
    }

    public Player.Status execute(Player player) {
        Place newPlace = player.getMap().move(player, dice.roll());
        player.moveToPlace(newPlace);
        return player.getCurrentPlace().visitedBy(player);
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
