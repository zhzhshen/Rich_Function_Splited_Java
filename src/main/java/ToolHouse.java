import java.util.ArrayList;
import java.util.List;

public class ToolHouse implements Place{
    private List<Item> items = new ArrayList() {
        {
            add(ROBOT);
            add(BOMB);
            add(ROAD_BLOCK);
        }
    };
    public boolean isInputRequired() {
        return true;
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public static final Item ROBOT = new Item("ROBOT", 100);
    public static final Item BOMB = new Item("BOMB", 200);
    public static final Item ROAD_BLOCK = new Item("ROAD_BLOCK", 300);

}
