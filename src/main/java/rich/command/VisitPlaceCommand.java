package rich.command;

import rich.GameMap;
import rich.Player;
import rich.place.Place;

public class VisitPlaceCommand implements Command {
    private Place place;

    public VisitPlaceCommand(Place currentPlace) {
        this.place = currentPlace;
    }

    public void action(GameMap map, Player player) {
        place.visitBy(player);
    }
}
