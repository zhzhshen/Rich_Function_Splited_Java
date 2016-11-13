import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rich.*;
import rich.command.UseBarricadeCommand;
import rich.command.UseBombCommand;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.place.Estate;
import rich.place.StartingPoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class BarricadeAndBombCommandTest {
    private GameMap map;
    private Player player;

    @Before
    public void before() {
        map = new GameMap(new StartingPoint(1), new Estate(2, 200), new Estate(3, 200));
        player = new Player(map, 0, 0);
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_wait_for_command_if_player_dont_have_barricade() {
        player.execute(new UseBarricadeCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(nullValue()));
    }

    @Test
    public void should_wait_for_command_succes_to_place_barricade() {
        Barricade barricade = new Barricade();
        player.addItem(barricade);
        player.execute(new UseBarricadeCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(barricade));
    }

    @Test
    public void should_wait_for_command_succes_to_place_bomb() {
        Bomb bomb = new Bomb();
        player.addItem(bomb);
        player.execute(new UseBombCommand(2));

        assertThat(player.getItems().size(), is(0));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(bomb));
    }

    @Test
    public void should_wait_for_command_fail_to_place_barricade_further_than_10_steps() {
        Barricade barricade = new Barricade();
        player.addItem(barricade);
        player.execute(new UseBarricadeCommand(11));

        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 11), is(nullValue()));
    }

    @Test
    public void should_wait_for_command_fail_to_place_bomb_on_barricade() {
        Barricade barricade = new Barricade();
        Bomb bomb = new Bomb();
        player.addItem(barricade);
        player.addItem(bomb);
        player.execute(new UseBarricadeCommand(2));

        assertThat(player.getItems().size(), is(1));
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(barricade));

        player.execute(new UseBombCommand(2));

        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 2), is(barricade));
    }

    @Test
    public void should_wait_for_command_fail_to_place_bomb_on_player() {
        Bomb bomb = new Bomb();
        player.addItem(bomb);
        player.execute(new UseBombCommand(3));

        assertThat(player.getItems().size(), is(1));
        assertThat(map.getItemAt(player.getCurrentPlace().getPosition() + 3), is(nullValue()));
    }

    @After
    public void after() {
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }
}
