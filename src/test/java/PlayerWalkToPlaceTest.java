import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.GameMap;
import rich.Player;
import rich.place.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerWalkToPlaceTest {
    GameMap map;
    Player player;
    Dice dice;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map);
        dice = () -> 1;
    }

    @Test
    public void should_wait_for_input_when_walk_to_empty_place() {
        when(map.move(eq(player), anyInt())).thenReturn(new Land());

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_RESPOND));
    }

    @Test
    public void should_turn_end_when_walk_to_hospital() {
        when(map.move(eq(player), anyInt())).thenReturn(new Hospital(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }

    @Test
    public void should_turn_end_when_walk_to_starting_point() {
        when(map.move(eq(player), anyInt())).thenReturn(new StartingPoint(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }

    @Test
    public void should_turn_end_when_walk_to_magic_house() {
        when(map.move(eq(player), anyInt())).thenReturn(new MagicHouse(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }

    @Test
    public void should_turn_end_when_walk_to_police() {
        when(map.move(eq(player), anyInt())).thenReturn(new Police(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }

    @Test
    public void should_wait_for_input_when_walk_to_gift_house() {
        when(map.move(eq(player), anyInt())).thenReturn(new GiftHouse(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_RESPOND));
    }
}
