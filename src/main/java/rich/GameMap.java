package rich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameMap {
    private List<Player> players = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    private HashMap<Integer, Item> items = new HashMap<>();

    public GameMap(Place... places) {
        this.places.addAll(Arrays.asList(places));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Place move(Player player, int steps) {
        return null;
    }

    public Item getItemAt(int position) {
        return items.get(moveStepForward(position, 0));
    }

    public Place getStartingPoint() {
        return places.stream().filter(place -> place instanceof StartingPoint).findFirst().get();
    }

    public boolean putItemStepsForward(Item item,int start, int steps) {
        int targetPosition = moveStepForward(start, steps);
        if (!items.containsKey(targetPosition)
                && !players.stream().anyMatch(player -> player.getCurrentPlace().getPosition() == targetPosition)) {
            items.put(targetPosition, item);
            return true;
        }
        return false;
    }

    private int moveStepForward(int start, int steps) {
        return (start + steps - 1) % places.size() + 1;
    }
}
