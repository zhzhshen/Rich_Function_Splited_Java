package rich;

public class UseBarricadeCommand implements Command{
    private Barricade barricade = new Barricade();
    private int steps;

    public UseBarricadeCommand(int steps) {
        this.steps = steps;
    }

    public Player.Status execute(Player player) {
        player.use(barricade, steps);
        return Player.Status.WAIT_FOR_COMMAND;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
