package rich.response;

import rich.Player;
import rich.place.Estate;

public interface BuyLandResponse {
    Response No = player -> {
        player.setMessage("放弃购买土地");
        return Player.Status.TURN_END;
    };
    Response YesToBuy = player -> {
        boolean isSuccess = false;
        Estate currentPlace = (Estate) player.getCurrentPlace();
        if (currentPlace.getOwner() == null) {
            isSuccess = player.buy();
        }

        if (isSuccess) {
            player.setMessage("成功购买土地");
        } else {
            player.setMessage("购买土地失败");
        }
        return Player.Status.TURN_END;
    };
    Response YesToBuild = player -> {
        if (player.build()) {
            player.setMessage("成功升级土地");
        } else {
            player.setMessage("成功升级土地");
        }
        return Player.Status.TURN_END;
    };
}
