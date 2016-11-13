package rich;

public class RollCommand implements Command {
    private Dice dice;

    public RollCommand(Dice dice) {
        this.dice = dice;
    }

    public Player.Status execute(Player player) {
        Place newPlace = player.getMap().move(player, dice.roll());
        player.moveToPlace(newPlace);
        return Player.Status.WAIT_FOR_RESPONSE;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
