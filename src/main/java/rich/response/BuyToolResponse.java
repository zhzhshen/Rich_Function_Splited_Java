package rich.response;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public interface BuyToolResponse {
    int PRICE_BARRICADE = 50;
    int PRICE_ROBOT = 30;
    int PRICE_BOMB = 50;

    Response Buy_Barricade = player -> {
        String message = "点数不够,购买道具路障失败\n";
        if (player.reducePoint(PRICE_BARRICADE)) {
            player.addItem(new Barricade());
            message = "成功购买道具路障\n";
        }
        Pair<Player.Status, String> toolHouseResponse = player.getCurrentPlace().visitedBy(player);
        return Pair.of(toolHouseResponse.fst, message + toolHouseResponse.snd);
    };
    Response Buy_Robot = player -> {
        String message = "点数不够,购买道具机器人失败\n";
        if (player.reducePoint(PRICE_ROBOT)) {
            player.addItem(new Robot());
            message = "成功购买道具机器人\n";
        }
        Pair<Player.Status, String> toolHouseResponse = player.getCurrentPlace().visitedBy(player);
        return Pair.of(toolHouseResponse.fst, message + toolHouseResponse.snd);
    };
    Response Buy_Bomb  = player -> {
        String message = "点数不够,购买道具炸弹失败\n";
        if (player.reducePoint(PRICE_BOMB)) {
            player.addItem(new Bomb());
             message = "成功购买道具炸弹";
        }
        Pair<Player.Status, String> toolHouseResponse = player.getCurrentPlace().visitedBy(player);
        return Pair.of(toolHouseResponse.fst, message + toolHouseResponse.snd);
    };
    Response No  = player -> Pair.of(Player.Status.TURN_END, "期待您再次光临!");
}
