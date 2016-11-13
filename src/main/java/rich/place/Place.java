package rich.place;

import rich.Player;

public interface Place {
    boolean isInputRequired(Player player);

    int getPosition();

    void visitBy(Player player);
}
