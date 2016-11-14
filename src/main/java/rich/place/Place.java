package rich.place;

import rich.Player;

public interface Place {
    Player.Status visitedBy(Player player);

    int getPosition();

    void print();
}
