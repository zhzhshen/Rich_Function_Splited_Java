import org.junit.Before;
import org.junit.Test;
import rich.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RollCommandTest {
    private final double INITIAL_BALANCE = 1000;
    private final double ESTATE_PICE = 200;
    private GameMap map;
    private Dice dice;
    private Place estate;
    private Player player;

    @Before
    public void before() {
        map = mock(GameMap.class);
        dice = mock(Dice.class);
        estate = new Estate(ESTATE_PICE);
        player = new Player(map, INITIAL_BALANCE);
    }
    @Test
    public void should_move_user_to_correspond_place() {
        when(map.move(eq(player), anyInt())).thenReturn(estate);

        player.execute(new RollCommand(dice));

        assertThat(player.getCurrentPlace(), is(estate));
    }

    @Test
    public void should_wait_for_respond_if_walk_to_empty_estate() {
        when(map.move(eq(player), anyInt())).thenReturn(estate);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        player.execute(new RollCommand(dice));

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_turn_end_if_walk_to_empty_estate_and_respond_no() {
        should_wait_for_respond_if_walk_to_empty_estate();

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));

        player.respond(BuyLandRespond.No);

        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_turn_end_if_walk_to_empty_estate_and_respond_yes() {
        should_wait_for_respond_if_walk_to_empty_estate();

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));

        player.respond(BuyLandRespond.YesToBuy);

        assertThat(estate.getOwner(), is(player));
        assertThat(player.getBalance(), is(INITIAL_BALANCE - ESTATE_PICE));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_turn_end_if_walk_to_empty_estate_and_respond_yes_without_enough_money() {
        should_wait_for_respond_if_walk_to_empty_estate();

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));

        player.reduceMoney(INITIAL_BALANCE);
        player.respond(BuyLandRespond.YesToBuy);

        assertThat(player.getBalance(), is(0.0));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_wait_for_respond_if_walk_to_own_estate() {
        when(map.move(eq(player), anyInt())).thenReturn(estate);

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));

        player.execute(new RollCommand(dice));

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_turn_end_if_walk_to_own_estate_respond_no() {
        should_wait_for_respond_if_walk_to_own_estate();

        player.respond(BuyLandRespond.No);

        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_turn_end_if_walk_to_own_estate_respond_yes() {
        should_wait_for_respond_if_walk_to_own_estate();

        player.respond(BuyLandRespond.YesToBuild);

        assertThat(estate.getLevel(), is(1));
        assertThat(player.getBalance(), is(INITIAL_BALANCE - ESTATE_PICE));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_turn_end_if_walk_to_own_estate_respond_yes_without_enough_money() {
        should_wait_for_respond_if_walk_to_own_estate();
        player.reduceMoney(INITIAL_BALANCE);

        player.respond(BuyLandRespond.YesToBuild);

        assertThat(estate.getLevel(), is(0));
        assertThat(player.getBalance(), is(0.0));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_turn_end_if_walk_to_own_estate_respond_yes_with_max_level() {
        should_wait_for_respond_if_walk_to_own_estate();
        estate.build();
        estate.build();
        estate.build();
        assertThat(estate.getLevel(), is(3));

        player.respond(BuyLandRespond.YesToBuild);

        assertThat(estate.getLevel(), is(3));
        assertThat(player.getBalance(), is(INITIAL_BALANCE));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }
}