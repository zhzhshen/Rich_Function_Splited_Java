package rich.command;

import rich.GameMap;
import rich.Player;

import static rich.Player.ControlStatus.TURN_END;

public class CommandNo implements Command {
    public void action(GameMap map, Player player) {
        player.setStatus(TURN_END);
    }
}
