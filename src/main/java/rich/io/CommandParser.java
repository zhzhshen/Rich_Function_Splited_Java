package rich.io;

import rich.Dice;
import rich.command.*;

public interface CommandParser {
    static Command parse(String commandString) {
        String[] splitedCommand = splitBySpace(commandString);
        String command = splitedCommand[0];
        String value = splitedCommand.length > 1 ? splitedCommand[1] : null;

        switch (command.toLowerCase()) {
            case "roll":
                return new RollCommand(new Dice());
            case "block":
                return new UseBarricadeCommand(Integer.valueOf(value));
            case "bomb":
                return new UseBombCommand(Integer.valueOf(value));
            case "robot":
                return new UseRobotCommand();
            case "sell":
                return new SellCommand(Integer.valueOf(value));
            case "selltool":
                return new SellToolCommand(Integer.valueOf(value));
            case "query":
                return new QueryCommand();
            case "help":
                return new HelpCommand();
            case "quit":
                return new QuitCommand();
        }

        return null;
    }

    static String[] splitBySpace(String command) {
        return command.split(" ");
    }
}
