package rich;

public class ToolHouse implements Place {
    public Player.Status visitedBy(Player player) {
        int cheapest = Math.min(Math.min(Robot.Price, Barricade.Price), Math.min(Robot.Price, Bomb.Price));
        return player.getPoint() < cheapest ? Player.Status.TURN_END: Player.Status.WAIT_FOR_RESPONSE;
    }
}
