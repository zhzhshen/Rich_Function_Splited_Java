import org.junit.Before;
import org.junit.Test;
import rich.GameMap;
import rich.Item.Bomb;
import rich.Item.Item;
import rich.Item.RoadBlock;
import rich.Item.Robot;
import rich.Player;
import rich.place.ToolHouse;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerBuyToolTest {
    private static final int ROBOT_PRICE = 30;
    private static final int BOMB_PRICE = 50;
    private static final int ROAD_BLOCK = 50;
    private static final int INITIAL_POINT = 100;
    ToolHouse toolHouse;
    GameMap map;
    Player player;
    private Item roadBlock = new RoadBlock(50);
    private Item robot = new Robot(30);
    private Item bomb = new Bomb(50);

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        toolHouse = new ToolHouse(1);
        player = new Player(map, 10000, 0);

        when(map.move(eq(player), anyInt())).thenReturn(toolHouse);

        player.roll(() -> 1);
    }

    @Test
    public void should_end_turn_when_say_no() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);

        player.sayNo();

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }


    @Test
    public void should_success_to_buy_roadblock_when_yes_with_enough_money() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);

        player.setPointBalance(INITIAL_POINT);
        player.sayYesToByTool(1);

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getItems().get(0), is(roadBlock));
        assertThat(player.getPoint(), is(INITIAL_POINT - ROAD_BLOCK));
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);
    }

    @Test
    public void should_success_to_buy_robot_when_yes_with_enough_money() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);

        player.setPointBalance(INITIAL_POINT);
        player.sayYesToByTool(2);

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getItems().get(0), is(robot));
        assertThat(player.getPoint(), is(INITIAL_POINT - ROBOT_PRICE));
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);
    }

    @Test
    public void should_success_to_buy_bomb_when_yes_with_enough_money() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);

        player.setPointBalance(INITIAL_POINT);
        player.sayYesToByTool(3);

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getItems().get(0), is(bomb));
        assertThat(player.getPoint(), is(INITIAL_POINT - BOMB_PRICE));
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);
    }

    @Test
    public void should_fail_to_buy_bomb_when_yes_without_enough_money() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);

        player.setPointBalance(0);
        player.sayYesToByTool(2);

        assertThat(player.getItems().size(), is(0));
        assertTrue(player.getPoint() == 0);
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);
    }

    @Test
    public void should_fail_to_buy_roadblock_when_yes_with_max_item() {
        playerBuyTenItems();
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);

        player.setPointBalance(INITIAL_POINT);
        player.sayYesToByTool(3);

        assertThat(player.getItems().size(), is(10));
        assertThat(player.getPoint(), is(INITIAL_POINT));
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_RESPOND);
    }

    private void playerBuyTenItems() {
        for (int i = 0; i < 10; i++) player.addItem(new Bomb(50));
    }
}
