package rich.command;

import rich.GameMap;
import rich.Player;
import rich.place.Land;
import rich.place.Place;

public class BuyLandCommand implements Command {
    private Place place;

    public BuyLandCommand(Place currentPlace) {

        this.place = currentPlace;
    }

    public void action(GameMap map, Player player) {
        Land land = (Land) place;
        if (land.getOwner()== null) {
            land.sellTo(player);
        } else if (land.getOwner().equals(player)) {
            land.builtBy(player);
        }
    }
}
