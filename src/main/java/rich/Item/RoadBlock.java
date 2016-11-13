package rich.Item;

import rich.GameMap;
import rich.Player;
import rich.place.Place;

public class RoadBlock implements Item {
    private int price;

    public RoadBlock(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Place trigger(GameMap map, Player player, int itemPosition) {
        return map.getPlace(itemPosition - 1);
    }

    public void use(GameMap map, Player player, int position) {
        if (player.getItems().contains(this)
                && !map.anyPlayerAt(position)
                && map.getDistance(player, position) <= 10
                && map.putItemAt(this, position)) {
            player.removeItem(this);
        }
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
