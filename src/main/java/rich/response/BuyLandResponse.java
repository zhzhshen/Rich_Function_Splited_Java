package rich.response;

import rich.Player;
import rich.place.Estate;

public interface BuyLandResponse {
    Response No = player -> Player.Status.TURN_END;
    Response YesToBuy = player -> {
        Estate currentPlace = (Estate) player.getCurrentPlace();
        if (currentPlace.getOwner() == null) {
            player.buy();
        }
        return Player.Status.TURN_END;
    };
    Response YesToBuild = player -> {
        player.build();
        return Player.Status.TURN_END;
    };
}
