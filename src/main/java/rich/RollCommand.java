package rich;

public class RollCommand implements Command {
    private Dice dice;

    public RollCommand(Dice dice) {
        this.dice = dice;
    }

    public Player.Status execute(Player player) {
        Place newPlace = player.getMap().move(player, dice.roll());
        player.moveToPlace(newPlace);
        Player owner = player.getCurrentPlace().getOwner();
        if (owner == null || owner.equals(player)) {
            return Player.Status.WAIT_FOR_RESPONSE;
        } else {
            player.getCurrentPlace().charge(player);
            return Player.Status.TURN_END;
        }
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }
}
