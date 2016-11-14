package rich;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final GameMap map;
    private final Map<Integer, Player> players = new HashMap<>();
    private int currentPlayer = 1;

    public Game(GameMap map, Player... players) {
        this.map = map;
        for (int i = 1; i <= players.length; i++) {
            this.players.put(i, players[i - 1]);
        }
    }

    public void startTurn() {
        if (!getActivePlayer().activate()) {
            endTurn();
            startTurn();
        }
    }

    public Player getActivePlayer() {
        return players.get(currentPlayer);
    }

    public void endTurn() {
        currentPlayer = nextPlayer();
    }

    private int nextPlayer() {
        return currentPlayer % players.size() + 1;
    }

    public boolean isGameOver() {
        Player activePlayer = getActivePlayer();
        if (activePlayer.isGameOver()) {
            players.remove(currentPlayer);
            if (players.size() == 1) {
                return true;
            }
        }
        return false;
    }
}
