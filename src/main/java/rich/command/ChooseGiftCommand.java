package rich.command;

import rich.GameMap;
import rich.Player;

public class ChooseGiftCommand implements Command{
    private int giftIndex;

    public ChooseGiftCommand(int giftIndex) {
        this.giftIndex = giftIndex;
    }

    public void action(GameMap map, Player player) {
        switch (giftIndex) {
            case 1:
                player.gainMoney(2000);
                player.setStatus(Player.ControlStatus.TURN_END);
                return;
            case 2:
                player.gainPoint(200);
                player.setStatus(Player.ControlStatus.TURN_END);
                return;
            case 3:
                player.evisu();
                player.setStatus(Player.ControlStatus.TURN_END);
                return;
        }
    }
}
