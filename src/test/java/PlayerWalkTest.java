import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerWalkTest {
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
        when(map.move(any(Place.class), anyInt())).thenReturn(new Land());

        player.roll(dice);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_INPUT));
    }

    @Test
    public void should_turn_end_when_walk_to_hospital() {
        when(map.move(any(Place.class), anyInt())).thenReturn(new Hospital());

        player.roll(dice);

        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }
}
