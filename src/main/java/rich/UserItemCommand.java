package rich;

import rich.Item.Item;
import rich.Item.Items;
import rich.Item.Robot;
import rich.command.Command;

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
        if (item instanceof Robot) {
            map.clearItems(player.getCurrentPlace().getPosition(), 10);
        } else if (player.getItems().contains(item)
                && !map.anyPlayerAt(position)
                && map.getDistance(player, position) <= 10
                && map.putItemAt(item, position)) {
            player.removeItem(item);

        }
    }
}
