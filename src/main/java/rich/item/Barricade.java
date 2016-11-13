package rich.item;

import rich.Player;
import rich.place.Place;

public class Barricade implements Item {
    public static final int Price = 50;

    public void use(Player player, int steps) {
        if (steps > 10) return;
        if (player.getItems().contains(this)
                && player.getMap().putItemStepsForward(this, player.getCurrentPlace().getPosition(), steps)) {
            player.getItems().remove(this);
        }
    }

    public int getPoint() {
        return Price;
    }

    public Place trigger(Player player, int position) {
        return player.getMap().getPlace(position);
    }

    public boolean equals(Object obj) {
        return obj instanceof Barricade;
    }
}
