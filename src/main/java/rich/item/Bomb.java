package rich.item;

import rich.Player;
import rich.place.Place;

public class Bomb implements Item {
    public static final int Price = 50;

    public String use(Player player, int steps) {
        if (steps > 10) return "放置失败,距离不可大于10!";
        if (player.getItems().stream().anyMatch(item -> item.equals(this))
                && player.getMap().putItemStepsForward(this, player.getCurrentPlace().getPosition(), steps)) {
            player.getItems().remove(this);
            return "放置成功!";
        }
        return "放置炸弹失败!";
    }

    public int getPoint() {
        return Price;
    }

    public Place trigger(Player player, int position) {
        player.burn();
        return player.getMap().getHospital();
    }

    public void print() {
        System.out.print("@");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bomb;
    }
}
