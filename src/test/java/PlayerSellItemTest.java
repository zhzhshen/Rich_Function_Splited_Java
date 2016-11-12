import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Item.Items;
import rich.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class PlayerSellItemTest {
    private GameMap map;
    private Player player1;
    private Game game;

    @Before
    public void before() {
        map = mock(GameMap.class);
        player1 = new Player(map);
        player1.addItem(Items.ROAD_BLOCK);
        game = new Game(map, player1);

        game.startTurn();
    }

    @Test
    public void should_success_to_sell_roadblock_when_sell_command() {
        player1.sellItem(1);

        assertThat(player1.getItems().size(), is(0));
        assertThat(player1.getPoint(), is(Items.ROAD_BLOCK.getPrice()));
    }

    @Test
    public void should_fail_to_sell_robot_when_not_have_one() {
        player1.sellItem(2);

        assertThat(player1.getItems().size(), is(1));
        assertThat(player1.getPoint(), is(0));
    }

    @Test
    public void should_success_to_sell_robot_when_sell_command() {
        player1.addItem(Items.ROBOT);
        player1.sellItem(2);

        assertThat(player1.getItems().size(), is(1));
        assertThat(player1.getPoint(), is(Items.ROBOT.getPrice()));
    }

    @Test
    public void should_success_to_sell_bomb_when_sell_command() {
        player1.addItem(Items.BOMB);
        player1.sellItem(3);

        assertThat(player1.getItems().size(), is(1));
        assertThat(player1.getPoint(), is(Items.BOMB.getPrice()));
    }

    @After
    public void after() {
        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }
}