package rich;

public class Bomb implements Item {
    public static final int Price = 50;

    public void use(Player player, int steps) {
        if (steps > 10) return;
        if (player.getItems().stream().anyMatch(item -> item.equals(this))
                && player.getMap().putItemStepsForward(this, player.getCurrentPlace().getPosition(), steps)) {
            player.getItems().remove(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bomb;
    }
}
