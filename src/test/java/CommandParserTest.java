import org.junit.Test;
import rich.io.CommandParser;
import rich.command.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CommandParserTest {
    private Command command;

    @Test
    public void should_return_roll_command() {
        command = CommandParser.parse("roll");

        assertThat(command instanceof RollCommand, is(true));
    }

    @Test
    public void should_return_barricade_command() {
        command = CommandParser.parse("block 2");

        assertThat(command.equals(new UseBarricadeCommand(2)), is(true));
    }

    @Test
    public void should_return_bomb_command() {
        command = CommandParser.parse("bomb 3");

        assertThat(command.equals(new UseBombCommand(3)), is(true));
    }

    @Test
    public void should_return_robot_command() {
        command = CommandParser.parse("robot");

        assertThat(command.equals(new UseRobotCommand()), is(true));
    }

    @Test
    public void should_return_sell_command() {
        command = CommandParser.parse("sell 5");

        assertThat(command.equals(new SellCommand(5)), is(true));
    }

    @Test
    public void should_return_sell_tool_command() {
        command = CommandParser.parse("sellTool 4");

        assertThat(command.equals(new SellToolCommand(4)), is(true));
    }

    @Test
    public void should_return_query_command() {
        command = CommandParser.parse("query");

        assertThat(command.equals(new QueryCommand()), is(true));
    }

    @Test
    public void should_return_help_command() {
        command = CommandParser.parse("help");

        assertThat(command.equals(new HelpCommand()), is(true));
    }

    @Test
    public void should_return_quit_command() {
        command = CommandParser.parse("quit");

        assertThat(command.equals(new QuitCommand()), is(true));
    }
}
