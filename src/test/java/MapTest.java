import org.junit.Before;
import org.junit.Test;
import rich.GameMap;
import rich.Player;
import rich.command.RollCommand;
import rich.command.UseBarricadeCommand;
import rich.command.UseBombCommand;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.place.Estate;
import rich.place.Hospital;
import rich.place.Place;
import rich.place.StartingPoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MapTest {
    GameMap map;
    Player player;
    Place startingPoint;
    Place estate;
    Place hospital;

    @Before
    public void before() {
        startingPoint = new StartingPoint(1);
        estate = new Estate(2, 200);
        hospital = new Hospital(3);

        map = new GameMap(startingPoint, estate, hospital);
        player = new Player(map, 0, 0);
    }

    @Test
    public void should_move_player_to_empty_estate() {
        player.execute(new RollCommand(() -> 1));

        assertThat(player.getCurrentPlace(), is(estate));
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_move_player_to_hospital() {
        player.execute(new RollCommand(() -> 2));

        assertThat(player.getCurrentPlace(), is(hospital));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }

    @Test
    public void should_blocked_at_empty_estate_by_barricade_and_wait_for_response() {
        player.addItem(new Barricade());
        player.execute(new UseBarricadeCommand(1));

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(2), is(new Barricade()));

        player.execute(new RollCommand(() -> 2));

        assertThat(player.getCurrentPlace(), is(estate));
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_RESPONSE));
    }

    @Test
    public void should_move_to_hospital_when_walk_by_a_bomb_and_turn_end() {
        player.addItem(new Bomb());
        player.execute(new UseBombCommand(1));

        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(2), is(new Bomb()));

        player.execute(new RollCommand(() -> 3));

        assertThat(player.getCurrentPlace(), is(hospital));
        assertThat(player.isInHospital(), is(true));
        assertThat(player.getStatus(), is(Player.Status.TURN_END));
    }
}
