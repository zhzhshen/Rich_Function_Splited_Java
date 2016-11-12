import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerBuyToolTest {
    private static final double THOUSAND = 1000;
    private static final double HUNDRED = 100;
    ToolHouse toolHouse;
    GameMap map;
    Player player;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        toolHouse = mock(ToolHouse.class);
        player = new Player(map);

        when(toolHouse.isInputRequired(eq(player))).thenReturn(true);
        when(map.move(any(Place.class), anyInt())).thenReturn(toolHouse);

        player.roll(() -> 1);
    }

    @Test
    public void should_end_turn_when_say_no() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);

        player.sayNo();

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }


    @Test
    public void should_success_to_buy_robot_when_yes_with_enough_money() {
        when(toolHouse.getItem(anyInt())).thenReturn(ToolHouse.ROBOT);
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);

        player.setCashBalance(THOUSAND);
        player.sayYesToByTool(1);

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getItems().get(0), is(ToolHouse.ROBOT));
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED));
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);
    }

    @Test
    public void should_fail_to_buy_bomb_when_yes_without_enough_money() {
        when(toolHouse.getItem(anyInt())).thenReturn(ToolHouse.BOMB);
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);

        player.setCashBalance(0);
        player.sayYesToByTool(2);

        assertThat(player.getItems().size(), is(0));
        assertTrue(player.getCashBalance() == 0);
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);
    }

    @Test
    public void should_fail_to_buy_roadblock_when_yes_with_max_item() {
        playerBuyTenItems();
        when(toolHouse.getItem(anyInt())).thenReturn(ToolHouse.ROAD_BLOCK);
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);

        player.setCashBalance(THOUSAND);
        player.sayYesToByTool(3);

        assertThat(player.getItems().size(), is(10));
        assertThat(player.getCashBalance(), is(THOUSAND));
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);
    }

    private void playerBuyTenItems() {
        for (int i = 0; i < 10; i++) player.addItem(ToolHouse.BOMB);
    }
}
