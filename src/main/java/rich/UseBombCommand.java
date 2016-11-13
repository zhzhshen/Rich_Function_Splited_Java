package rich;

public class UseBombCommand implements Command {
    private int steps;

    public UseBombCommand(int steps) {
        this.steps = steps;
    }

    public Player.Status execute(Player player) {
        player.use(new Bomb(), steps);
        return Player.Status.WAIT_FOR_COMMAND;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
