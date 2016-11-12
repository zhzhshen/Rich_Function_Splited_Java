public interface Buyable {
    void setOwner(Player owner);

    Player getOwner();

    double getUnitPrice();

    int getLevel();

    void setLevel(int level);

    boolean isMaxLevel();

    void action(Player player);
}
