package rich.response;

import com.sun.tools.javac.util.Pair;
import rich.Player;

public interface Response {
    Pair<Player.Status, String> execute(Player player);
}
