package rich.Item;

public class Items {
    public static final Item ROAD_BLOCK = new RoadBlock(50);
    public static final Item ROBOT = new Robot(30);
    public static final Item BOMB = new Bomb(50);

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
