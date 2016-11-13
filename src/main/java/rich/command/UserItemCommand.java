package rich.command;

import rich.GameMap;
import rich.Item.Item;
import rich.Item.Items;
import rich.Player;

public class UserItemCommand implements Command {
    public static final int USE_ROAD_BLOCK = 1;
    public static final int USE_ROBOT = 2;
    public static final int USE_BOMB = 3;
    private final Item item;
    private final int position;

    public UserItemCommand(int item, int position) {
        this.item = Items.getItem(item);
        this.position = position;
    }

    public void action(GameMap map, Player player) {
        item.use(map, player, position);
    }
}
