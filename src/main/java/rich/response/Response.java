package rich.response;

import rich.Player;

public interface Response {
    Player.Status execute(Player player);
}
