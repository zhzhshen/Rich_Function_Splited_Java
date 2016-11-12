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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadBlock roadBlock = (RoadBlock) o;

        if (price != roadBlock.price) return false;
        return position == roadBlock.position;

    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + position;
        return result;
    }
}
