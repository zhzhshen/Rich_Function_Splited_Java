package rich;

public class BuyLandRespond {
    public static Response No = player -> Player.Status.TURN_END;
    public static Response YesToBuy = player -> {
        Estate currentPlace = (Estate) player.getCurrentPlace();
        if (currentPlace.getOwner() == null) {
            player.buy();
        }
        return Player.Status.TURN_END;
    };
    public static Response YesToBuild = player -> {
        player.build();
        return Player.Status.TURN_END;
    };
}
