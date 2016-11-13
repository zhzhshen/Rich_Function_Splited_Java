package rich;

public class MagicHouse implements Place {
    public Player.Status visitedBy(Player player) {
        return Player.Status.TURN_END;
    }
}
