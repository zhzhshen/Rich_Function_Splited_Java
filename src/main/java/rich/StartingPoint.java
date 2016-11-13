package rich;

public class StartingPoint implements Place {
    private int position;

    public StartingPoint(int position) {
        this.position = position;
    }

    public Player.Status visitedBy(Player player) {
        return Player.Status.TURN_END;
    }

    public int getPosition() {
        return position;
    }
}
