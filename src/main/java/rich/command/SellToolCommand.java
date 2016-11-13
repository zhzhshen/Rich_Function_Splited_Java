package rich.command;

import rich.Player;
import rich.response.Response;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;

public class SellToolCommand implements Command {
    private int index;

    public SellToolCommand(int index) {
        this.index = index;
    }

    public Player.Status execute(Player player) {
        switch (index) {
            case 1:
                player.sellItem(new Barricade());
                break;
            case 2:
                player.sellItem(new Robot());
                break;
            case 3:
                player.sellItem(new Bomb());
                break;
        }
        return Player.Status.WAIT_FOR_COMMAND;
    }

    public Player.Status respond(Player player, Response response) {
        return response.execute(player);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellToolCommand that = (SellToolCommand) o;

        return index == that.index;

    }

    @Override
    public int hashCode() {
        return index;
    }
}
