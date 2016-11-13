import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Item.Bomb;
import rich.Item.Item;
import rich.Item.RoadBlock;
import rich.Item.Robot;
import rich.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PlayerSellItemTest {
    private GameMap map;
    private Player player1;
    private Game game;
    private Item roadBlock = new RoadBlock(50);
    private Item bomb = new Bomb(50);
    private Item robot = new Robot(30);

    @Before
    public void before() {
        map = mock(GameMap.class);
        player1 = new Player(map, 10000, 0);
        player1.addItem(roadBlock);
        game = new Game(map, player1);

        game.startTurn();
    }

    @Test
    public void should_success_to_sell_roadblock_when_sell_command() {
        player1.sellItem(1);

        assertThat(player1.getItems().size(), is(0));
        assertThat(player1.getPoint(), is(roadBlock.getPrice()));
    }

    @Test
    public void should_fail_to_sell_robot_when_not_have_one() {
        player1.sellItem(2);

        assertThat(player1.getItems().size(), is(1));
        assertThat(player1.getPoint(), is(0));
    }

    @Test
    public void should_success_to_sell_robot_when_sell_command() {
        player1.addItem(robot);
        player1.sellItem(2);

        assertThat(player1.getItems().size(), is(1));
        assertThat(player1.getPoint(), is(robot.getPrice()));
    }

    @Test
    public void should_success_to_sell_bomb_when_sell_command() {
        player1.addItem(bomb);
        player1.sellItem(3);

        assertThat(player1.getItems().size(), is(1));
        assertThat(player1.getPoint(), is(bomb.getPrice()));
    }

    @After
    public void after() {
        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }
}