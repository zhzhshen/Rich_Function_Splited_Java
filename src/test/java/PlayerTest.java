import com.sun.tools.javac.util.Pair;
import org.junit.Before;
import org.junit.Test;
import rich.GameMap;
import rich.Player;
import rich.command.Command;
import rich.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private Player player;
    private Command command;
    private GameMap map;

    @Before
    public void before() {
        map = mock(GameMap.class);
        command = mock(Command.class);
        player = new Player(map, (double) 0, 0);
    }

    @Test
    public void should_wait_for_command_if_execute_responsiveness_command() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        when(command.execute(eq(player))).thenReturn(Pair.of(Player.Status.WAIT_FOR_COMMAND, null));

        player.execute(command);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_wait_for_command_if_execute_responsive_command() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        when(command.execute(eq(player))).thenReturn(Pair.of(Player.Status.WAIT_FOR_RESPONSE, null));

        player.execute(command);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_end_turn_after_execute_command() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        when(command.execute(eq(player))).thenReturn(Pair.of(Player.Status.TURN_END, null));

        player.execute(command);

        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_wait_for_command_after_respond_to_command() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        when(command.execute(eq(player))).thenReturn(Pair.of(Player.Status.WAIT_FOR_RESPONSE, null));

        player.execute(command);

        Response response = mock(Response.class);
        when(command.respond(eq(player), eq(response))).thenReturn(Pair.of(Player.Status.WAIT_FOR_COMMAND, null));

        player.respond(response);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_end_turn_after_respond_to_command() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        when(command.execute(eq(player))).thenReturn(Pair.of(Player.Status.WAIT_FOR_RESPONSE, null));

        player.execute(command);

        Response response = mock(Response.class);
        when(command.respond(eq(player), eq(response))).thenReturn(Pair.of(Player.Status.TURN_END, null));

        player.respond(response);

        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }
}
