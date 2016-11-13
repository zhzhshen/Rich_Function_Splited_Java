package rich.Item;

public class RoadBlock implements Item {
    private int price;

    public RoadBlock(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoadBlock roadBlock = (RoadBlock) o;

        return price == roadBlock.price;

    }

    @Override
    public int hashCode() {
        return price;
    }
}
