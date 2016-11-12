package rich.command;

import rich.place.Land;
import rich.place.Place;
import rich.Player;

public class BuyLandCommand implements Command {
    public void action(Place place, Player player) {
        Land land = (Land) place;
        if (land.getOwner()== null) {
            land.sellTo(player);
        } else if (land.getOwner().equals(player)) {
            land.builtBy(player);
        }
    }
}
