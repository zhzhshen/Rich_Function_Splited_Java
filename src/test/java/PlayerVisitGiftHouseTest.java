import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.GameMap;
import rich.Player;
import rich.place.GiftHouse;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerVisitGiftHouseTest {
    public static final double INITIAL_BALANCE = 1000;
    public static final double CASH_GIFT = 2000;
    public static final int POINT_GIFT = 200;
    GameMap map;
    Player player;
    Dice dice;

    @Before
    public void before() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map, 10000, 0);
        dice = () -> 1;

        player.setCashBalance(1000);
        when(map.move(eq(player), anyInt())).thenReturn(new GiftHouse(1));

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_RESPOND));
    }

    @Test
    public void should_success_to_choose_money() {
        player.chooseGift(1);

        assertThat(player.getCashBalance(), is(INITIAL_BALANCE + CASH_GIFT));
        assertThat(player.getPoint(), is(0));
        assertThat(player.hasEvisu(), is(false));
    }

    @Test
    public void should_success_to_choose_point() {
        player.chooseGift(2);

        assertThat(player.getPoint(), is(POINT_GIFT));
        assertThat(player.getCashBalance(), is(INITIAL_BALANCE));
        assertThat(player.hasEvisu(), is(false));
    }

    @Test
    public void should_success_to_choose_evisu() {
        player.chooseGift(3);

        assertThat(player.getCashBalance(), is(INITIAL_BALANCE));
        assertThat(player.getPoint(), is(0));
        assertThat(player.hasEvisu(), is(true));
    }


    @After
    public void should_end_turn_after_choose_a_gift() throws Exception{
        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }
}
