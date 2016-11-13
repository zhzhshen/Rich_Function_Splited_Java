import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TurnTest {
    GameMap map;
    Game game;
    Player player1;
    Player player2;

    @Before
    public void before() {
        map = new GameMap();
        map.init();
        player1 = new Player(map, 0, 0);
        player2 = new Player(map, 0, 0);
        game = new Game(map, player1, player2);
    }

    @Test
    public void should_start_first_player_wait_for_command() {
        game.startTurn();

        assertThat(game.getActivePlayer(), is(player1));
        assertThat(player1.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_switch_turn_between_players() {
        game.startTurn();
        assertThat(game.getActivePlayer(), is(player1));
        assertThat(player1.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player2));
        assertThat(player1.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player1));
        assertThat(player1.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_skip_3_turns_when_in_hospital() {
        player1.burn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player2));
        assertThat(player1.getStatus(), is(Player.Status.TURN_END));
        assertThat(player2.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player2));
        assertThat(player1.getStatus(), is(Player.Status.TURN_END));
        assertThat(player2.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player2));
        assertThat(player1.getStatus(), is(Player.Status.TURN_END));
        assertThat(player2.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player1));
        assertThat(player1.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_skip_2_turns_when_in_prison() {
        player1.prisoned();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player2));
        assertThat(player1.getStatus(), is(Player.Status.TURN_END));
        assertThat(player2.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player2));
        assertThat(player1.getStatus(), is(Player.Status.TURN_END));
        assertThat(player2.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        game.endTurn();

        game.startTurn();
        assertThat(game.getActivePlayer(), is(player1));
        assertThat(player1.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }
}
