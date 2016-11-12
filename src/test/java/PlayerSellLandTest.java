import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerSellLandTest {
    private static final double INITIAL_BALABCE = 1000;
    private static final double LAND_PRICE = 100;
    private GameMap map;
    private Player player1;
    private Player player2;
    private Game game;
    private Land land;

    @Before
    public void before() {
        map = mock(GameMap.class);
        land = new Land(1, LAND_PRICE, 0);
        player1 = new Player(map);
        player1.setCashBalance(INITIAL_BALABCE);
        player2 = new Player(map);
        game = new Game(map, player1, player2);

        when(map.getPlace(anyInt())).thenReturn(land);
        game.startTurn();
    }

    @Test
    public void should_success_to_sell_property_when_sell_command() {
        player1.sellLand(1);

        assertThat(player1.getCashBalance(), is(INITIAL_BALABCE + LAND_PRICE));
        assertThat(land.getOwner(), is(nullValue()));
        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }
}
