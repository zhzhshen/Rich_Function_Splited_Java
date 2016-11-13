import org.junit.Before;
import org.junit.Test;
import rich.GameMap;
import rich.Player;
import rich.place.Land;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerPayTollTest {
    private static final double THOUSAND = 1000;
    private static final double HUNDRED = 100;
    Land land = new Land(1, HUNDRED, 0);
    GameMap map;
    Player player;
    Player owner;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map, 10000, 0);
        player.setCashBalance(THOUSAND);
        owner = new Player(map, 10000, 0);
        owner.setCashBalance(THOUSAND);
        land.setOwner(owner);

        when(map.move(eq(player), anyInt())).thenReturn(land);
    }


    @Test
    public void should_pay_half_unit_price_when_walk_to_others_land_level_0() {
        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED / 2));
        assertThat(owner.getCashBalance(), is(THOUSAND + HUNDRED / 2));
    }

    @Test
    public void should_pay_unit_price_when_walk_to_others_land_level_1() {
        land.setLevel(1);
        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED));
        assertThat(owner.getCashBalance(), is(THOUSAND + HUNDRED));
    }

    @Test
    public void should_pay_unit_price_when_walk_to_others_land_level_3() {
        land.setLevel(3);
        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
        assertThat(player.getCashBalance(), is(THOUSAND - HUNDRED * 4));
        assertThat(owner.getCashBalance(), is(THOUSAND + HUNDRED * 4));
    }

    @Test
    public void should_not_pay_toll_when_walk_to_others_whom_in_hospital() {
        owner.burn();

        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
        assertThat(player.getCashBalance(), is(THOUSAND));
        assertThat(owner.getCashBalance(), is(THOUSAND));
    }

    @Test
    public void should_not_pay_toll_when_walk_to_others_whom_in_prison() {
        owner.prisoned();

        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
        assertThat(player.getCashBalance(), is(THOUSAND));
        assertThat(owner.getCashBalance(), is(THOUSAND));
    }

    @Test
    public void should_not_pay_toll_when_has_evisu() {
        player.evisu();

        player.roll(() -> 1);

        assertEquals(player.getControlStatus(), Player.ControlStatus.TURN_END);
        assertThat(player.getCashBalance(), is(THOUSAND));
        assertThat(owner.getCashBalance(), is(THOUSAND));
    }
}
