package rich;

public class StartingPoint implements Place{
    public Player.Status visitedBy(Player player) {
        return Player.Status.TURN_END;
    }
}
