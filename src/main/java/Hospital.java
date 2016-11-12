public class Hospital implements Place {
    public boolean isInputRequired(Player player) {
        return false;
    }

    public void action(Player player, Command command) {
        command.action(this, player);
    }
}
