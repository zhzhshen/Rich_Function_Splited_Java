public interface Place {
    boolean isInputRequired(Player player);

    void action(Player player, int index);
}
