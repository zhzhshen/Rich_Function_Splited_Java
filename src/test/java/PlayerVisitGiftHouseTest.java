import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerVisitGiftHouseTest {
    public static final double INITIAL_BALANCE = 1000;
    public static final double CASH_GIFT = 2000;
    GameMap map;
    Player player;
    Dice dice;

    @Before
    public void setUp() throws Exception {
        map = mock(GameMap.class);
        player = new Player(map);
        dice = () -> 1;

        player.setCashBalance(1000);
        when(map.move(any(Place.class), anyInt())).thenReturn(new GiftHouse());

        player.roll(dice);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_INPUT));
    }

    @Ignore
    @Test
    public void should_success_to_choose_money() {
        player.chooseGift(1);

        assertThat(player.getCashBalance(), is(INITIAL_BALANCE + CASH_GIFT));
    }

    @After
    public void should_end_turn_after_choose_a_gift() throws Exception{
        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }
}
