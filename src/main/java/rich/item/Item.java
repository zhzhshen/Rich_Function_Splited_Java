package rich.item;

import rich.Player;
import rich.place.Place;

public interface Item {
    String use(Player player, int steps);

    int getPoint();

    Place trigger(Player player, int position);

    void print();
}
