package rich.command;

import rich.*;
import rich.Item.Item;
import rich.Item.Items;
import rich.place.Place;
import rich.place.ToolHouse;

public class BuyItemCommand implements Command {
    private int itemIndex;

    public BuyItemCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void action(Place place, Player player) {
        ToolHouse toolHouse = (ToolHouse) place;
        Item item = Items.getItem(itemIndex);
        if (player.getItems().size() < Player.MAX_ITEM
                && player.reducePoint(item.getPrice())) {
            player.addItem(item);
        }
    }
}
