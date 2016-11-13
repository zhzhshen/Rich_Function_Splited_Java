package rich;

public class Police implements Place {
    public Player.Status visitedBy(Player player) {
        player.prisoned();
        return Player.Status.TURN_END;
    }
}
