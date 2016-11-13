package rich;

public class GiftHouse implements Place{
    public Player.Status visitedBy(Player player) {
        return Player.Status.WAIT_FOR_RESPONSE;
    }
}
