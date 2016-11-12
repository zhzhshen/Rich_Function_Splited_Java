package rich.Item;

public class Items {
    public static Item getItem(int index) {
        switch (index) {
            case 1:
                return new RoadBlock(50);
            case 2:
                return new Robot(30);
            case 3:
                return new Bomb(50);
        }
        return null;
    }
}
