package rich.command;

import rich.GameMap;
import rich.Item.Item;
import rich.Item.Items;
import rich.Player;

public class SellItemCommand implements Command {
    private int itemIndex;

    public SellItemCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void action(GameMap map, Player player) {
        Item item = Items.getItem(itemIndex);
        if (player.removeItem(item)) {
            player.gainPoint(item.getPrice());
        }
    }
}
