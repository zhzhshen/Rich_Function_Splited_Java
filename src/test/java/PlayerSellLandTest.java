import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
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
    private Land otherLand;

    @Before
    public void before() {
        map = mock(GameMap.class);
        player1 = new Player(map);
        player1.setCashBalance(INITIAL_BALABCE);
        player2 = new Player(map);
        game = new Game(map, player1, player2);
        land = new Land(1, LAND_PRICE, 0);
        land.setOwner(player1);
        otherLand = new Land(2, LAND_PRICE, 1);
        otherLand.setOwner(player2);

        when(map.getPlace(eq(1))).thenReturn(land);
        when(map.getPlace(eq(2))).thenReturn(otherLand);
        game.startTurn();
    }

    @Test
    public void should_success_to_sell_land_when_sell_command() {
        player1.sellLand(1);

        assertThat(player1.getCashBalance(), is(INITIAL_BALABCE + LAND_PRICE * 2));
        assertThat(land.getOwner(), is(nullValue()));
    }

    @Test
    public void should_fail_to_sell_land_when_belong_to_other() {
        player1.sellLand(2);

        assertThat(player1.getCashBalance(), is(INITIAL_BALABCE));
        assertThat(otherLand.getOwner(), is(player2));
    }

    @Test
    public void should_success_to_sell_land_when_level_three() {
        land.setLevel(3);
        when(map.getPlace(eq(1))).thenReturn(land);
        player1.sellLand(1);

        assertThat(player1.getCashBalance(), is(INITIAL_BALABCE + LAND_PRICE * 8));
        assertThat(land.getOwner(), is(nullValue()));
    }

    @After
    public void after() {
        assertThat(game.getCurrentPlayer(), is(player1));
        assertThat(player1.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }
}
