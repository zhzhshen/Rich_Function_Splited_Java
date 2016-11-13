import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.GameMap;
import rich.Player;
import rich.place.Police;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerVisitPoliceTest {
    GameMap map;
    Player player;
    Dice dice;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map, 10000, 0);
        dice = () -> 1;

        when(map.move(eq(player), anyInt())).thenReturn(new Police(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }

    @Test
    public void should_be_put_in_prison_when_walk_to_police() {
        assertThat(player.isInPrison(), is(true));
    }
}
