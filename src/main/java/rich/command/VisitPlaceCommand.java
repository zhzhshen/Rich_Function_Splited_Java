package rich.command;

import rich.GameMap;
import rich.Player;
import rich.place.Land;
import rich.place.Place;
import rich.place.Police;

public class VisitPlaceCommand implements Command {
    private Place place;

    public VisitPlaceCommand(Place currentPlace) {

        this.place = currentPlace;
    }

    public void action(GameMap map, Player player) {
        if (place instanceof Land) {
            Land land = (Land) place;
            if (!land.getOwner().isInHospital()
                    && !land.getOwner().isInPrison()
                    && !player.hasEvisu()) {
                land.charge(player);
            }
        }

        if (place instanceof Police) {
            player.prisoned();
        }
    }
}
