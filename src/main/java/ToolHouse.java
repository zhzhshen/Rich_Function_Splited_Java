import java.util.ArrayList;
import java.util.List;

public class ToolHouse implements Place {
    private List<Item> items = new ArrayList() {
        {
            add(ROBOT);
            add(BOMB);
            add(ROAD_BLOCK);
        }
    };
    public boolean isInputRequired(Player player) {
        return true;
    }

    public void action(Player player, int toolIndex) {
        Item item = items.get(toolIndex - 1);
        if (player.getItems().size() < Player.MAX_ITEM
                && player.reduceMoney(item.getPrice())) {
            player.addItem(item);
        }
    }

    public static final Item ROBOT = new Item("ROBOT", 100);
    public static final Item BOMB = new Item("BOMB", 200);
    public static final Item ROAD_BLOCK = new Item("ROAD_BLOCK", 300);

}
