import org.junit.Test;
import rich.Command;
import rich.Player;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandStreamTest {

    @Test
    public void should_no_further_command_can_be_accepted_when_roll_to_responsiveness_place() {
        Command roll = mock(Command.class);
        Player player = new Player(roll);

        when(roll.take(eq("roll"))).thenReturn(roll);
        when(roll.execute(eq(player))).thenReturn(Collections.EMPTY_LIST);

        assertThat(player.canTakeCommands(), is(true));

        player.execute("roll");

        assertThat(player.canTakeCommands(), is(false));
    }

    @Test
    public void should_response_can_be_accepted_when_roll_to_responsive_place() {
        Command roll = mock(Command.class);
        Command buyEstateCommand = mock(Command.class);
        Player player = new Player(roll);

        when(roll.take(eq("roll"))).thenReturn(roll);
        when(roll.execute(eq(player))).thenReturn(Arrays.asList(buyEstateCommand));
        when(buyEstateCommand.take(any())).thenReturn(buyEstateCommand);
        when(buyEstateCommand.execute(eq(player))).thenReturn(Collections.emptyList());

        assertThat(player.canTakeCommands(), is(true));

        player.execute("roll");

        assertThat(player.canTakeCommands(), is(true));

        player.execute("1");

        assertThat(player.canTakeCommands(), is(false));
    }
}
