package rich;

public interface Place {
    double getPrice();

    Player getOwner();

    void sellTo(Player player);

    void build();

    int getLevel();

    boolean isMaxLevel();

    void charge(Player player);
}
