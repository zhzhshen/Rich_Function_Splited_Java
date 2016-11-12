package rich.command;

import rich.GameMap;
import rich.Player;

public class SendGiftCommand implements Command{
    private int giftIndex;

    public SendGiftCommand(int giftIndex) {
        this.giftIndex = giftIndex;
    }

    public void action(GameMap map, Player player) {
        switch (giftIndex) {
            case 1:
                player.gainMoney(2000);
                return;
            case 2:
                player.gainPoint(200);
                return;
            case 3:
                player.evisu();
        }
    }
}
