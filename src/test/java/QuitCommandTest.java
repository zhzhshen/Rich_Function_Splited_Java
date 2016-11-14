import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Player;
import rich.command.QuitCommand;
import rich.place.StartingPoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QuitCommandTest {
    private GameMap map;
    private Player player;
    private Game game;

    @Before
    public void before() {
        map = new GameMap(new StartingPoint(1));
        player = new Player(map, 0, 0);
        game = new Game(map, player);

        game.startTurn();
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_exit_game_when_quit() {
        assertThat(game.isGameOver(), is(false));

        player.execute(new QuitCommand());

        assertThat(game.isGameOver(), is(true));
    }
}
