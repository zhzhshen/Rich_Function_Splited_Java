import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.*;
import rich.command.SellCommand;
import rich.place.Estate;
import rich.place.StartingPoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class SellCommandTest {
    double INITIAL_BALANCE = 1000;
    double ESTATE_PRICE = 200;
    private GameMap map;
    private Player player;
    private StartingPoint startingPoint;
    private Estate estate1;

    @Before
    public void before() {
        startingPoint = new StartingPoint(1);
        estate1 = new Estate(2, ESTATE_PRICE);
        map = new GameMap(startingPoint, estate1);
        player = new Player(map, INITIAL_BALANCE, 0);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_wait_for_command_failed_sell_estate_if_player_respond_invalid_number() {
        player.execute(new SellCommand(4));

        assertThat(player.getBalance(), is(INITIAL_BALANCE));
    }

    @Test
    public void should_wait_for_command_failed_sell_estate_if_estate_is_empty() {
        player.execute(new SellCommand(2));

        assertThat(player.getBalance(), is(INITIAL_BALANCE));
    }

    @Test
    public void should_wait_for_command_failed_sell_estate_if_estate_is_others() {
        estate1.sellTo(mock(Player.class));

        player.execute(new SellCommand(2));

        assertThat(player.getBalance(), is(INITIAL_BALANCE));
    }

    @Test
    public void should_wait_for_command_success_sell_own_estate_0_level() {
        estate1.sellTo(player);
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        assertThat(player.getBalance(), is(INITIAL_BALANCE));

        player.execute(new SellCommand(2));

        assertThat(player.getBalance(), is(INITIAL_BALANCE + ESTATE_PRICE));
    }

    @Test
    public void should_wait_for_command_success_sell_own_estate_3_level() {
        estate1.sellTo(player);
        estate1.build();
        estate1.build();
        estate1.build();
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        assertThat(player.getBalance(), is(INITIAL_BALANCE));
        assertThat(estate1.getLevel(), is(3));

        player.execute(new SellCommand(2));

        assertThat(player.getBalance(), is(INITIAL_BALANCE + ESTATE_PRICE * 8));
    }

    @After
    public void after() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

}
