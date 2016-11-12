package rich.command;

import rich.GameMap;
import rich.Player;

public interface Command {
    void action(GameMap map, Player player);
}
