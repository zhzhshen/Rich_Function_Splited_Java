package rich.command;

import rich.place.Place;
import rich.Player;

public interface Command {
    void action(Place place, Player player);
}
