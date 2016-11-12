public class GiftHouse implements Place {
    public boolean isInputRequired(Player player) {
        return true;
    }

    public void action(Player player, Command command) {
        command.action(this, player);
    }
}
