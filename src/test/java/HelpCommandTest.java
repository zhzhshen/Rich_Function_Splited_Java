import org.junit.Before;
import org.junit.Test;
import rich.Game;
import rich.GameMap;
import rich.Player;
import rich.command.HelpCommand;
import rich.place.StartingPoint;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HelpCommandTest {
    private GameMap map;
    private Player player;
    private Game game;

    @Before
    public void before() {
        map = new GameMap(new StartingPoint(1));
        player = new Player(map, 0, 0);
        game = new Game(map, player);

        game.startTurn();
        assertThat(player.getStatus(), is(Player.Status.WAIT_FOR_COMMAND));
    }

    @Test
    public void should_wait_for_command_if_player_help() {
        String message = player.execute(new HelpCommand());

        String help;
        help =  "roll         掷骰子命令，行走1~6步。步数由随即算法产生。   \n" +
                "block n      玩家拥有路障后，可将路障放置到离当前位置前后10步的距离，任一玩家经过路障，都将被拦截。该道具一次有效。n 前后的相对距离，负数表示后方。\n" +
                "bomb n       可将路障放置到离当前位置前后10步的距离，任一玩家j 经过在该位置，将被炸伤，送往医院，住院三天。n 前后的相对距离，负数表示后方。\n" +
                "robot        使用该道具，可清扫前方路面上10步以内的其它道具，如炸弹、路障。\n" +
                "sell x       出售自己的房产，x 地图上的绝对位置，即地产的编号。\n" +
                "sellTool x   出售道具，x 道具编号\n" +
                "query        显示自家资产信息   \n" +
                "help         查看命令帮助   \n" +
                "quit         强制退出";

        assertThat(message, is(help));
    }
}
