package rich.command;

import rich.GameMap;
import rich.Player;
import rich.place.Land;

public class SellLandCommand implements Command {
    private int placeIndex;

    public SellLandCommand(int placeIndex) {
        this.placeIndex = placeIndex;
    }

    public void action(GameMap map, Player player) {
        Land land = (Land) map.getPlace(placeIndex);
        if (player.equals(land.getOwner())) {
            player.gainMoney(land.getPrice() * (land.getLevel() + 1) * 2);
            land.setLevel(0);
            land.setOwner(null);
        }
    }
}
