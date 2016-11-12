package rich.command;

import rich.place.Land;
import rich.place.Place;
import rich.Player;

public class SellLandCommand implements Command {
    public void action(Place place, Player player) {
        Land land = (Land) place;
        if (player.equals(land.getOwner())) {
            player.gainMoney(land.getPrice() * (land.getLevel() + 1) * 2);
            land.setLevel(0);
            land.setOwner(null);
        }
    }
}
