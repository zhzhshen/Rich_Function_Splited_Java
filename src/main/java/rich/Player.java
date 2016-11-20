package rich;

import java.util.Arrays;
import java.util.List;

public class Player {
    private List<Command> commands;

    public Player(Command... commands) {
        this.commands = Arrays.asList(commands);
    }

    public boolean canTakeCommands() {
        return !commands.isEmpty();
    }

    public void execute(String command) {
        this.commands = commands.stream().map(c -> c.take(command)).filter(c -> c != null).findFirst().get().execute(this);
    }
}
