package rich.command;

import rich.GameMap;
import rich.Item.Item;
import rich.Item.Items;
import rich.Player;

public class BuyItemCommand implements Command {
    private int itemIndex;

    public BuyItemCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void action(GameMap map, Player player) {
        Item item = Items.getItem(itemIndex);
        if (player.getItems().size() < Player.MAX_ITEM
                && player.reducePoint(item.getPrice())) {
            player.addItem(item);
        }
        player.setStatus(Player.ControlStatus.WAIT_FOR_RESPOND);
    }
}
