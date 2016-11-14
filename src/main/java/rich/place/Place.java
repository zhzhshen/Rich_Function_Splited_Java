package rich.place;

import com.sun.tools.javac.util.Pair;
import rich.Player;

public interface Place {
    Pair<Player.Status, String> visitedBy(Player player);

    int getPosition();

    void print();
}
