import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Item.Bomb;
import rich.Item.RoadBlock;
import rich.Item.Robot;
import rich.Player;
import rich.UserItemCommand;
import rich.place.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class UseItemTest {
    private Game game;
    private GameMap map;
    private Player player;
    Bomb bomb = new Bomb(50);
    RoadBlock roadBlock = new RoadBlock(50);
    Robot robot = new Robot(30);
    private Place startingPoint;
    private Place land;
    private Place hospital;
    private Place toolHouse;

    @Before
    public void before() {
        startingPoint = new StartingPoint(1);
        land = new Land(2, 1, 0);
        hospital = new Hospital(3);
        toolHouse = new ToolHouse(4);
        map = new GameMap(startingPoint, land, land, land, land, land, land, land, land, land, hospital, toolHouse);
        player = new Player(map);
        player.addItem(bomb);
        game = new Game(map, player);

        game.startTurn();

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_success_to_place_bomb() {
        player.useItem(UserItemCommand.USE_BOMB, 2);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(2), is(bomb));
    }

    @Test
    public void should_fail_to_place_bomb_on_player() {
        assertThat(player.getCurrentPlace().getPosition(), is(1));
        player.useItem(UserItemCommand.USE_BOMB, 1);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(1), is(nullValue()));
    }

    @Test
    public void should_fail_to_place_bomb_when_further_than_ten_steps() {
        player.useItem(UserItemCommand.USE_BOMB, 12);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(12), is(nullValue()));
    }

    @Test
    public void should_fail_to_place_bomb_on_roadblock() {
        player.addItem(roadBlock);
        player.useItem(UserItemCommand.USE_ROAD_BLOCK, 2);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(2), is(roadBlock));

        player.useItem(UserItemCommand.USE_BOMB, 2);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(2), is(roadBlock));
    }

    @Test
    public void should_success_to_place_road_block() {
        player.addItem(roadBlock);
        player.useItem(UserItemCommand.USE_ROAD_BLOCK, 2);

        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(2), is(roadBlock));
    }

    @Test
    public void should_success_to_use_robot() {
        player.addItem(robot);
        player.useItem(UserItemCommand.USE_BOMB, 2);
        map.putItemAt(roadBlock, 12);

        assertThat(map.getItemAt(2), is(bomb));
        assertThat(map.getItemAt(12), is(roadBlock));

        player.useItem(UserItemCommand.USE_ROBOT, 0);

        System.out.println(player.getItems());
        assertThat(player.getControlStatus(), is(Player.ControlStatus.WAIT_FOR_COMMAND));
        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(2), is(nullValue()));
        assertThat(map.getItemAt(12), is(roadBlock));
    }
}
