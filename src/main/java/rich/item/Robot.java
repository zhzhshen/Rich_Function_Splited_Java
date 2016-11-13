package rich.item;

import rich.Player;

public class Robot implements Item {
    public static final int Price = 30;

    public void use(Player player, int steps) {
        if (player.getItems().stream().anyMatch(item -> item.equals(this))) {
            player.getMap().clearItems(player.getCurrentPlace().getPosition(), 10);
            player.getItems().remove(this);
        }
    }

    @Override
    public int getPoint() {
        return Price;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Robot;
    }
}
