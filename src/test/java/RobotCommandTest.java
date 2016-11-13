import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class RobotCommandTest {
    private GameMap map;
    private Player player;
    private Bomb bomb;

    @Before
    public void before() {
        map = new GameMap(new StartingPoint(1), new Estate(2, 200), new Estate(3, 200));
        player = new Player(map, 0, 0);

        bomb = new Bomb();
        player.addItem(bomb);
        player.execute(new UseBombCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(bomb));
    }

    @Test
    public void should_wait_for_command_if_player_dont_have_robot() {
        player.execute(new UseRobotCommand());

        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(bomb));
    }

    @Test
    public void should_wait_for_command_cleared_items_10_step_forward_() {
        player.addItem(new Robot());
        assertThat(player.getItems().size(), is(1));

        player.execute(new UseRobotCommand());

        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(nullValue()));
    }

    @After
    public void after() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

}
