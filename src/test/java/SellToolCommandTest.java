import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.*;
import rich.command.SellToolCommand;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class SellToolCommandTest {
    final int INITIAL_POINT = 100;
    private GameMap map;
    private Player player;
    private Bomb bomb = new Bomb();
    private Robot robot = new Robot();
    private Barricade barricade = new Barricade();

    @Before
    public void before() {
        map = mock(GameMap.class);
        player = new Player(map, 0, INITIAL_POINT);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_fail_to_sell_barricade_when_not_have() {
        player.execute(new SellToolCommand(1));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT));
    }

    @Test
    public void should_fail_to_sell_robot_when_not_have() {
        player.execute(new SellToolCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT));
    }

    @Test
    public void should_success_to_sell_barricade() {
        player.addItem(barricade);
        assertThat(player.getItems().size(), is(1));
        player.execute(new SellToolCommand(1));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Barricade.Price));

        player.execute(new SellToolCommand(1));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Barricade.Price));
    }

    @Test
    public void should_success_to_sell_robot() {
        player.addItem(robot);
        assertThat(player.getItems().size(), is(1));
        player.execute(new SellToolCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Robot.Price));

        player.execute(new SellToolCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Robot.Price));
    }


    @Test
    public void should_success_to_sell_barricade_twice_when_have_two() {
        player.addItem(barricade);
        player.addItem(barricade);
        assertThat(player.getItems().size(), is(2));

        player.execute(new SellToolCommand(1));

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getPoint(), is(INITIAL_POINT + Barricade.Price));

        player.execute(new SellToolCommand(1));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Barricade.Price * 2));
    }

    @Test
    public void should_success_to_sell_barricade_after_robot() {
        player.addItem(barricade);
        player.addItem(robot);
        assertThat(player.getItems().size(), is(2));

        player.execute(new SellToolCommand(1));

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getPoint(), is(INITIAL_POINT + Barricade.Price));

        player.execute(new SellToolCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Barricade.Price + Robot.Price));
    }

    @Test
    public void should_fail_to_sell_bomb_when_not_have() {
        player.execute(new SellToolCommand(3));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT));
    }

    @Test
    public void should_success_to_sell_bomb() {
        player.addItem(bomb);
        assertThat(player.getItems().size(), is(1));
        player.execute(new SellToolCommand(3));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Bomb.Price));

        player.execute(new SellToolCommand(1));
        player.execute(new SellToolCommand(2));
        player.execute(new SellToolCommand(3));

        assertThat(player.getItems().size(), is(0));
        assertThat(player.getPoint(), is(INITIAL_POINT + Bomb.Price));
    }

    @After
    public void after() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }
}
