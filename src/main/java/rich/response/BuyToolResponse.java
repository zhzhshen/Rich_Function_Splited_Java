package rich.response;

import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public interface BuyToolResponse {
    int PRICE_BARRICADE = 50;
    int PRICE_ROBOT = 30;
    int PRICE_Bomb = 50;
    Response Buy_Barricade = player -> {
        if (player.reducePoint(PRICE_BARRICADE)) {
            player.addItem(new Barricade());
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    Response Buy_Robot = player -> {
        if (player.reducePoint(PRICE_ROBOT)) {
            player.addItem(new Robot());
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    Response Buy_Bomb  = player -> {
        if (player.reducePoint(PRICE_Bomb)) {
            player.addItem(new Bomb());
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    Response No  = player -> Player.Status.TURN_END;;
}
