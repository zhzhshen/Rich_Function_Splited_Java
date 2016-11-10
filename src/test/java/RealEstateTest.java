import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RealEstateTest {
    final int BALANCE = 1000;
    final int PRICE = 200;
    final int LEVEL = 0;

    @Test
    public void should_success_to_buy_free_real_estate() {
        RealEstate realEstate = new RealEstate(PRICE);
        Player player = new Player(BALANCE);

        player.buy(realEstate);

        assertThat(realEstate.getOwner(), is(player));
        assertThat(player.getCashBalance(), is(BALANCE - PRICE));
    }

    @Test
    public void should_fail_to_buy_real_estate_if_short_of_cash() {
        RealEstate realEstate = new RealEstate(PRICE);
        Player player = new Player(0);

        player.buy(realEstate);

        assertTrue(realEstate.getOwner() == null);
        assertEquals(player.getCashBalance(), 0);
    }

    @Test
    public void should_fail_to_buy_occupied_real_estate() {
        RealEstate realEstate = new RealEstate(PRICE);
        Player player = new Player(BALANCE);
        Player owner = new Player(BALANCE);
        owner.buy(realEstate);

        player.buy(realEstate);

        assertEquals(realEstate.getOwner(), owner);
        assertEquals(player.getCashBalance(), BALANCE);
    }

    @Test
    public void should_success_to_build() {
        RealEstate realEstate = new RealEstate(PRICE, LEVEL);
        Player player = new Player(BALANCE);

        player.buy(realEstate);
        player.build(realEstate);

        assertEquals(realEstate.getOwner(), player);
        assertEquals(player.getCashBalance(), BALANCE - PRICE * 2);
        assertEquals(realEstate.getLevel(), LEVEL + 1);
    }

    @Test
    public void should_fail_to_build_others() {
        RealEstate realEstate = new RealEstate(PRICE, LEVEL);
        Player player = new Player(BALANCE);
        Player owner = new Player(BALANCE);
        owner.buy(realEstate);

        player.build(realEstate);

        assertEquals(realEstate.getOwner(), owner);
        assertEquals(player.getCashBalance(), BALANCE);
        assertEquals(realEstate.getLevel(), LEVEL);
    }

    @Test
    public void should_fail_to_build_max_level() {
        RealEstate realEstate = new RealEstate(PRICE, RealEstate.MAX_LEVEL);
        Player player = new Player(BALANCE);

        player.buy(realEstate);
        player.build(realEstate);

        assertEquals(realEstate.getOwner(), player);
        assertEquals(player.getCashBalance(), BALANCE - PRICE);
        assertEquals(realEstate.getLevel(), RealEstate.MAX_LEVEL);
    }

    @Test
    public void should_success_to_pay_level_0() {
        RealEstate realEstate = new RealEstate(PRICE, 0);
        Player player = new Player(BALANCE);
        Player owner = new Player(BALANCE);
        owner.buy(realEstate);

        player.pay(realEstate);

        assertEquals(realEstate.getOwner(), owner);
        assertEquals(player.getCashBalance(), BALANCE - PRICE / 2);
    }

    @Test
    public void should_success_to_pay_level_1() {
        RealEstate realEstate = new RealEstate(PRICE, 1);
        Player player = new Player(BALANCE);
        Player owner = new Player(BALANCE);
        owner.buy(realEstate);

        player.pay(realEstate);

        assertEquals(realEstate.getOwner(), owner);
        assertEquals(player.getCashBalance(), BALANCE - PRICE);
    }
}
