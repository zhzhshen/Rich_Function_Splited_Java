public class Police implements Place {
    public boolean isInputRequired(Player player) {
        return false;
    }

    public void action(Player player, int index) {
        player.prisoned();
    }
}