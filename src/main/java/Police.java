public class Police implements Place {
    public boolean isInputRequired(Player player) {
        return false;
    }

    public void actionOn(Player player) {
        player.prisoned();
    }
}
