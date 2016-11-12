package rich.Item;

public class Robot implements Item {
    private int price;
    private int position;

    public Robot(int price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public int getPrice() {
        return price;
    }
}
