package rich.Item;

public class RoadBlock implements Item {
    private int price;
    private int position;

    public RoadBlock(int price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public int getPrice() {
        return price;
    }
}
