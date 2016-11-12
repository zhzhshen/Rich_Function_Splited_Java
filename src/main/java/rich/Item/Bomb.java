package rich.Item;

public class Bomb implements Item {
    private int price;
    private int position;

    public Bomb(int price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public int getPrice() {
        return price;
    }
}
