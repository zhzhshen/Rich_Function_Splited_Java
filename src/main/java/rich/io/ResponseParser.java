package rich.io;

import rich.Player;
import rich.place.Estate;
import rich.place.GiftHouse;
import rich.place.Place;
import rich.place.ToolHouse;
import rich.response.BuyLandResponse;
import rich.response.BuyToolResponse;
import rich.response.ChooseGiftResponse;
import rich.response.Response;

public interface ResponseParser {
    static Response parse(Player currentPlayer, String stringValue) {
        Place place = currentPlayer.getCurrentPlace();
        if (place instanceof ToolHouse) {
            int value = Integer.valueOf(stringValue);
            switch (value) {
                case 1:
                    return BuyToolResponse.Buy_Barricade;
                case 2:
                    return BuyToolResponse.Buy_Robot;
                case 3:
                    return BuyToolResponse.Buy_Bomb;
            }
        } else if (place instanceof GiftHouse) {
            int value = Integer.valueOf(stringValue);
            switch (value) {
                case 1:
                    return ChooseGiftResponse.Money;
                case 2:
                    return ChooseGiftResponse.Point;
                case 3:
                    return ChooseGiftResponse.Evisu;
            }

        } else if (place instanceof Estate) {
            Estate estate = (Estate) place;
            if (estate.getOwner() == null) {
                switch (stringValue.toLowerCase()) {
                    case "y":
                        return BuyLandResponse.YesToBuy;
                    case "n":
                        return BuyLandResponse.No;
                }
            } else {
                switch (stringValue) {
                    case "y":
                        return BuyLandResponse.YesToBuild;
                    case "n":
                        return BuyLandResponse.No;
                }
            }

        }
        return null;
    }
}
