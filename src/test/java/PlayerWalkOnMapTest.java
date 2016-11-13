import org.junit.Before;
import org.junit.Test;
import rich.*;
import rich.Item.Bomb;
import rich.Item.RoadBlock;
import rich.place.Hospital;
import rich.place.Land;
import rich.place.StartingPoint;
import rich.place.ToolHouse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
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

    @Test
    public void should_stop_at_hospital_when_reach_road_block() {
        RoadBlock roadBlock = new RoadBlock(50);
        player.addItem(roadBlock);
        player.useItem(UserItemCommand.USE_ROAD_BLOCK, 3);

        when(dice.roll()).thenReturn(3);

        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(hospital));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
        assertThat(map.getItemAt(3), is(nullValue()));
    }

    @Test
    public void should_stop_at_empty_land_and_able_to_buy_when_reach_road_block() {
        RoadBlock roadBlock = new RoadBlock(50);
        player.addItem(roadBlock);
        player.useItem(UserItemCommand.USE_ROAD_BLOCK, 2);

        when(dice.roll()).thenReturn(3);

        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(land));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_RESPOND));
        assertThat(map.getItemAt(2), is(nullValue()));
    }

    @Test
    public void should_in_hospital_when_reach_bomb() {
        Bomb bomb = new Bomb(50);
        player.addItem(bomb);
        player.useItem(UserItemCommand.USE_BOMB, 4);

        when(dice.roll()).thenReturn(4);

        player.roll(dice);

        assertThat(player.getCurrentPlace(), is(hospital));
        assertThat(player.isInHospital(), is(true));
        assertThat(map.getItemAt(4), is(nullValue()));
        assertThat(player.getControlStatus(), is(Player.ControlStatus.TURN_END));
    }
}
