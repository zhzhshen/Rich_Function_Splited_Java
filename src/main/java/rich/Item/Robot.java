package rich.Item;

public class Robot implements Item {
    private int price;

    public Robot(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;

        return price == robot.price;

    }

    @Override
    public int hashCode() {
        return price;
    }
}
