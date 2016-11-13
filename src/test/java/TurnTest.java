import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class TurnTest {
    private GameMap map;
    private Player player1;
    private Player player2;
    private Game game;

    @Before
    public void before() {
        map = mock(GameMap.class);
        player1 = new Player(map, 10000, 0);
        player2 = new Player(map, 10000, 0);
        game = new Game(map, player1, player2);

    }

    @Test
    public void should_wait_for_command() {
        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_take_turns_among_all_players() {
        game.startTurn();
        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        game.endTurn();

        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player2));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        game.endTurn();
    }

    @Test
    public void should_skip_turn_when_in_hospital() {
        player1.burn();

        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player2));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        assertThat(player1.getSkipTurns(), is(3));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_skip_turn_and_reduce_round_when_in_prison() {
        player1.prisoned();

        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player2));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        assertThat(player1.getSkipTurns(), is(2));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player2));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        assertThat(player1.getSkipTurns(), is(1));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();

        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getSkipTurns(), is(0));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player2.getControlStatus(), is(Player.ControlStatus.INACTIVE));
        game.endTurn();
    }
}
