package rich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private GameMap map;
    private List<Player> players = new ArrayList<>();
    private int currentPlayer;

    public Game(GameMap map, Player... players) {
        this.map = map;
        this.players.addAll(Arrays.asList(players));
        this.map.addPlayers(players);
        currentPlayer = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void startTurn() {
        if (!getCurrentPlayer().activate()) {
            endTurn();
            startTurn();
        }
    }

    public void endTurn() {
        getCurrentPlayer().deactivate();
        currentPlayer = nextPlayer();
    }

    private int nextPlayer() {
        return (currentPlayer + 1) % players.size();
    }
}
