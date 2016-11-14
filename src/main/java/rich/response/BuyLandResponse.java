package rich.response;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.place.Estate;

public interface BuyLandResponse {
    Response No = player -> Pair.of(Player.Status.TURN_END, "放弃购买土地");
    Response YesToBuy = player -> {
        String message = "";
        Estate currentPlace = (Estate) player.getCurrentPlace();
        if (currentPlace.getOwner() == null) {
            message = player.buy();
        }
        return Pair.of(Player.Status.TURN_END, message);
    };
    Response YesToBuild = player -> {
        return Pair.of(Player.Status.TURN_END, player.build());
    };
}
