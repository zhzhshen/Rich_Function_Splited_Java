import org.junit.Test;
import rich.Dice;
import rich.GameMap;
import rich.Player;
import rich.place.Mine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerGainPointTest {
    public static final double INITIAL_BALANCE = 1000;
    public static final double CASH_GIFT = 2000;
    GameMap map;
    Player player;
    Dice dice;

    @Test
    public void should_success_to_gain_money() {
        map = mock(GameMap.class);
        player = new Player(map, 10000, 0);
        dice = () -> 1;

        when(map.move(eq(player), eq(1))).thenReturn(new Mine(1, 50));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
        assertThat(player.getPoint(), is(50));
    }
}
