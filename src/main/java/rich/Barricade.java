package rich;

public class Barricade implements Item {
    public static final int Price = 50;

    public void use(Player player, int steps) {
        if (steps > 10) return;
        if (player.getItems().contains(this)
                && player.getMap().putItemStepsForward(this, player.getCurrentPlace().getPosition(), steps)) {
            player.getItems().remove(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Barricade;
    }
}
