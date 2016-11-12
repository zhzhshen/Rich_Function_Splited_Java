public class GiftHouse implements Place {
    public boolean isInputRequired(Player player) {
        return true;
    }

    public void action(Player player, int giftIndex) {
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
