package rich;

import java.util.List;

public interface Command {

    Command take(String command);

    List<Command> execute(Player player);
}
