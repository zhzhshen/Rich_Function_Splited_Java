package rich.item;

import rich.Player;

public interface Item {
    void use(Player player, int steps);

    int getPoint();
}
