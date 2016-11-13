import org.junit.Before;
import org.junit.Test;
import rich.GameMap;
import rich.Player;
import rich.place.Land;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerBuyLandTest {
    private static final double THOUSAND = 1000;
    private static final double HUNDRED = 100;
    Land land = new Land(1, HUNDRED, 0);
    GameMap map;
    Player player;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map, 10000, 0);

        when(map.move(eq(player), anyInt())).thenReturn(land);

        player.roll(() -> 1);
    }

    @Test
    public void should_end_turn_when_say_no() {
        player.sayNo();

        assertEquals(land.getOwner(), null);
        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }

    @Test
    public void should_success_to_buy_when_say_yes_with_enough_money() {
        player.setCashBalance(THOUSAND);
        player.sayYes();

        assertThat(land.getOwner(), is(player));
        assertThat(player.getLands().size(), is(1));
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED));
        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }

    @Test
    public void should_fail_to_buy_when_say_yes_without_enough_money() {
        player.setCashBalance(0);
        player.sayYes();

        assertEquals(land.getOwner(), null);
        assertThat(player.getLands().size(), is(0));
        assertTrue(player.getCashBalance() == 0);
        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }
}
