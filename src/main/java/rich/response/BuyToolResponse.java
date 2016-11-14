package rich.response;

import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public interface BuyToolResponse {
    int PRICE_BARRICADE = 50;
    int PRICE_ROBOT = 30;
    int PRICE_BOMB = 50;

    Response Buy_Barricade = player -> {
        if (player.reducePoint(PRICE_BARRICADE)) {
            player.addItem(new Barricade());
            System.out.println("成功购买道具路障");
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    Response Buy_Robot = player -> {
        if (player.reducePoint(PRICE_ROBOT)) {
            player.addItem(new Robot());
            System.out.println("成功购买道具机器人");
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    Response Buy_Bomb  = player -> {
        if (player.reducePoint(PRICE_BOMB)) {
            player.addItem(new Bomb());
            System.out.println("成功购买道具炸弹");
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    Response No  = player -> {
        System.out.println("期待您再次光临!");
        return Player.Status.TURN_END;
    };
}
