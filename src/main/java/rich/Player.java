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

    public void execute(String commandString) {
        commands.stream().map(c -> c.take(commandString)).filter(c -> c != null).findFirst().map(c -> this.commands = c.execute(this)).orElse(null);
    }
}
