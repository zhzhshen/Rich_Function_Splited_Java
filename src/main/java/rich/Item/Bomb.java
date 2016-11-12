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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bomb bomb = (Bomb) o;

        if (price != bomb.price) return false;
        return position == bomb.position;

    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + position;
        return result;
    }
}
