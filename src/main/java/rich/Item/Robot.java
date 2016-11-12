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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;

        if (price != robot.price) return false;
        return position == robot.position;

    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + position;
        return result;
    }
}
