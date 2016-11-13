package rich;

import rich.item.Item;
import rich.place.*;

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

    public void init() {
        this.places.addAll(Arrays.asList(new StartingPoint(1),
                new Estate(2, 200),
                new Hospital(3),
                new MagicHouse(4),
                new Police(5),
                new ToolHouse(6),
                new GiftHouse(7),
                new Estate(8, 300)));
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Place move(Player player, int steps) {
        int current = player.getCurrentPlace().getPosition();
        for (int i = 0; i < steps; i++) {
            if (items.containsKey(current)) {
                Item item = items.get(current);
                return item.trigger(player, current);
            }
            current = moveStepForward(current, 1);
        }
        return getPlace(current);
    }

    public Item getItemAt(int position) {
        return items.get(moveStepForward(position, 0));
    }

    public Place getStartingPoint() {
        return places.stream().filter(place -> place instanceof StartingPoint).findFirst().get();
    }

    public boolean putItemStepsForward(Item item, int start, int steps) {
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

    public void clearItems(int position, int steps) {
        int current = position + 1;
        for (int i = 0; i < steps; i++) {
            items.remove(current);
            current = moveStepForward(current, 1);
        }
    }

    public Place getPlace(int position) {
        return places.stream().filter(place -> place.getPosition() == position).findFirst().orElse(null);
    }

    public Place getHospital() {
        return places.stream().filter(place -> place instanceof Hospital).findFirst().get();
    }
}
