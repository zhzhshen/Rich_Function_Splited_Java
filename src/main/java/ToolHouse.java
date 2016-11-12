import java.util.ArrayList;
import java.util.List;

public class ToolHouse implements Place {
    public List<Item> items = new ArrayList() {
        {
            add(0, ROAD_BLOCK);
            add(1, ROBOT);
            add(2, BOMB);
        }
    };

    public List<Item> getItems() {
        return items;
    }
    public boolean isInputRequired(Player player) {
        return true;
    }

    public void action(Player player, Command command) {
        command.action(this, player);
    }

    public static final Item ROAD_BLOCK = new Item("ROAD_BLOCK", 50);
    public static final Item ROBOT = new Item("ROBOT", 30);
    public static final Item BOMB = new Item("BOMB", 50);

}
