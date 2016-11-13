package rich.Item;

import rich.GameMap;
import rich.Player;
import rich.place.Place;

public interface Item {
    int getPrice();

    Place trigger(GameMap map, Player player, int position);

    void use(GameMap map, Player player, int position);
}
