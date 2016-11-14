package rich.response;

import com.sun.tools.javac.util.Pair;
import rich.Player;

public interface ChooseGiftResponse {
    int GIFT_MONEY = 2000;
    int GIFT_POINT = 200;
    Response Money = player -> {
        player.gainMoney(GIFT_MONEY);
        return Pair.of(Player.Status.TURN_END, "获得" + GIFT_MONEY + "元");
    };
    Response Point = player -> {
        player.gainPoint(GIFT_POINT);
        return Pair.of(Player.Status.TURN_END, "获得" + GIFT_POINT + "点");
    };
    Response Evisu = player -> {
        player.evisu();
        return Pair.of(Player.Status.TURN_END, "获得福神5回合!");
    };
}
