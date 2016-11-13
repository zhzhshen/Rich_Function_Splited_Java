package rich.item;

import rich.Player;
import rich.place.Place;

public interface Item {
    void use(Player player, int steps);

    int getPoint();

    Place trigger(Player player, int position);
}
