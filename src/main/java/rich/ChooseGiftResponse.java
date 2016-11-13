package rich;

public interface ChooseGiftResponse {
    int GIFT_MONEY = 2000;
    int GIFT_POINT = 200;
    Response Money = player -> {
        player.gainMoney(GIFT_MONEY);
        return Player.Status.TURN_END;
    };
    Response Point = player -> {
        player.gainPoint(GIFT_POINT);
        return Player.Status.TURN_END;
    };
    Response Evisu = player -> {
        player.evisu();
        return Player.Status.TURN_END;
    };
}
