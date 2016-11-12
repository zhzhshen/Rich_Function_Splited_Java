import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerBuildLandTest {
    private static final double THOUSAND = 1000;
    private static final double HUNDRED = 100;
    private static final int LEVEL = 0;
    Land land = new Land(HUNDRED, LEVEL);
    GameMap map;
    Player player;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map);
        land.setOwner(player);

        when(map.move(any(Place.class), anyInt())).thenReturn(land);

        player.roll(() -> 1);
    }


    @Test
    public void should_end_turn_when_say_no() {
        assertEquals(player.getControlStatus(), Player.ControlStatus.WAIT_FOR_INPUT);

        player.sayNo();

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }

    @Test
    public void should_success_to_build_when_say_yes_with_enough_money() {
        player.setCashBalance(THOUSAND);
        player.sayYes();

        assertThat(land.getLevel(), is(LEVEL + 1));
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED));
        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }

    @Test
    public void should_fail_to_build_when_say_yes_without_enough_money() {
        player.setCashBalance(0);
        player.sayYes();

        assertThat(land.getLevel(), is(LEVEL));
        assertTrue(player.getCashBalance() == 0);
        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }

    @Test
    public void should_end_turn_when_max_level() {
        player.setCashBalance(THOUSAND);

        assertThat(land.getLevel(), is(0));
        assertThat(player.getCashBalance(), is(THOUSAND));

        player.roll(() -> 1);
        player.sayYes();

        assertThat(land.getLevel(), is(1));
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED));

        player.roll(() -> 1);
        player.sayYes();

        assertThat(land.getLevel(), is(2));
        assertThat(player.getCashBalance(), is(THOUSAND - 2 * HUNDRED));

        player.roll(() -> 1);
        player.sayYes();

        assertThat(land.getLevel(), is(3));
        assertThat(player.getCashBalance(), is(THOUSAND - 3 * HUNDRED));

        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
    }
}
