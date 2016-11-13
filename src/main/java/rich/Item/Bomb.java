package rich.Item;

public class Bomb implements Item {
    private int price;

    public Bomb(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bomb bomb = (Bomb) o;

        return price == bomb.price;

    }

    @Override
    public int hashCode() {
        return price;
    }
}
