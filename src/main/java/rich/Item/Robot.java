package rich.Item;

import rich.GameMap;
import rich.Player;
import rich.place.Place;

public class Robot implements Item {
    private final int CLEAR_DISTANCE = 10;
    private int price;

    public Robot(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Place trigger(GameMap map, Player player, int position) {
        return null;
    }

    public void use(GameMap map, Player player, int position) {
        map.clearItems(player.getCurrentPlace().getPosition(), CLEAR_DISTANCE);
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
