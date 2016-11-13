package rich.command;

import rich.GameMap;
import rich.Player;
import rich.place.Place;

public class VisitPlaceCommand implements Command {
    private Place place;

    public void action(GameMap map, Player player) {
        place = player.getCurrentPlace();
        place.visitBy(player);
        player.setStatus(Player.ControlStatus.TURN_END);
    }
}
