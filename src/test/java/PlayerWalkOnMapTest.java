import org.junit.Before;
import org.junit.Test;
import rich.*;
import rich.place.Hospital;
import rich.place.Land;
import rich.place.StartingPoint;
import rich.place.ToolHouse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerWalkOnMapTest {
    private GameMap map;
    private Player player;
    private Dice dice;
    private Land land;
    private Hospital hospital;
    private ToolHouse toolHouse;
    private StartingPoint startingPoint;

    @Before
    public void setUp() throws Exception {
        dice = mock(Dice.class);
        startingPoint = new StartingPoint(1);
        land = new Land(2, 1, 0);
        hospital = new Hospital(3);
        toolHouse = new ToolHouse(4);
        map = new GameMap(startingPoint, land, hospital, toolHouse);
        player = new Player(map);
    }

    @Test
    public void should_move_to_empty_land_when_rolled_one_step() {
        when(dice.roll()).thenReturn(1);

        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(land));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_RESPOND));
    }

    @Test
    public void should_move_to_hostipal_when_rolled_two_step() {
        when(dice.roll()).thenReturn(2);

        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(hospital));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }

    @Test
    public void should_move_to_toolHouse_when_rolled_twice_each_one_and_two_step() {
        when(dice.roll()).thenReturn(1);
        player.roll(dice);

        when(dice.roll()).thenReturn(2);
        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(toolHouse));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_RESPOND));
    }

    @Test
    public void should_move_to_starting_point__when_rolled_four_step() {
        when(dice.roll()).thenReturn(4);

        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(startingPoint));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }
}
