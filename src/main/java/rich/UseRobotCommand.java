package rich;

public class UseRobotCommand implements Command {
    public Player.Status execute(Player player) {
        player.useRobot();
        return Player.Status.WAIT_FOR_COMMAND;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
