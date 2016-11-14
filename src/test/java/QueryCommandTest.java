import org.junit.Before;
import org.junit.Test;
import rich.Dice;
import rich.GameMap;
import rich.Player;
import rich.command.QueryCommand;
import rich.command.RollCommand;
import rich.item.Bomb;
import rich.place.Estate;
import rich.response.BuyLandResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QueryCommandTest {
    private GameMap map;
    private Player player;
    private Bomb bomb;
    private Dice dice;
    private int INITIAL_POINT = 100;
    private double INITIAL_BALANCE = 1000;
    private Estate estate1;

    @Before
    public void before() {
        dice = mock(Dice.class);
        estate1 = new Estate(2, 200);
        map = mock(GameMap.class);
        player = new Player(map, INITIAL_BALANCE, INITIAL_POINT);

        bomb = new Bomb();
        player.addItem(bomb);

        when(map.move(eq(player), anyInt())).thenReturn(estate1);
        player.execute(new RollCommand(dice));

        player.respond(BuyLandResponse.YesToBuy);
    }

    @Test
    public void should_wait_for_command_if_player_query() {
        String message = player.execute(new QueryCommand());

        StringBuilder sb = new StringBuilder();
        sb.append("资金:800元\n");
        sb.append("点数:100点\n");
        sb.append("地产:空地1处;茅屋0处;洋房0处;摩天楼0处\n");
        sb.append("道具:路障0个;炸弹1个;机器娃娃0个\n");
        assertThat(message, is(sb.toString()));
    }

}
