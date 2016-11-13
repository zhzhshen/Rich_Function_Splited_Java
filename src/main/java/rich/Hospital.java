package rich;

public class Hospital implements Place {
    public Player.Status visitedBy(Player player) {
        return Player.Status.TURN_END;
    }
}
