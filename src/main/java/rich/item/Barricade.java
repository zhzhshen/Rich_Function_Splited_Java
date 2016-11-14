package rich.item;

import rich.Player;
import rich.place.Place;

public class Barricade implements Item {
    public static final int Price = 50;

    public String use(Player player, int steps) {
        if (steps > 10) return "放置失败,距离不可大于10!";
        if (player.getItems().contains(this)
                && player.getMap().putItemStepsForward(this, player.getCurrentPlace().getPosition(), steps)) {
            player.getItems().remove(this);
            return "放置成功!";
        }
        return "放置路障失败!";
    }

    public int getPoint() {
        return Price;
    }

    public Place trigger(Player player, int position) {
        return player.getMap().getPlace(position);
    }

    public void print() {
        System.out.print("#");
    }

    public boolean equals(Object obj) {
        return obj instanceof Barricade;
    }
}
