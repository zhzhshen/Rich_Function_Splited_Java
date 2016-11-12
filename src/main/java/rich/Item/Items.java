package rich.Item;

public class Items {
    public static final Item ROAD_BLOCK = new Item("ROAD_BLOCK", 50);
    public static final Item ROBOT = new Item("ROBOT", 30);
    public static final Item BOMB = new Item("BOMB", 50);

    public static Item getItem(int index) {
        switch (index) {
            case 1:
                return ROAD_BLOCK;
            case 2:
                return ROBOT;
            case 3:
                return BOMB;
        }
        return null;
    }
}
