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
        int index = 1;
        places.add(new StartingPoint(index++));
        int i = index;
        for (; index < i + 13; index++) {
            places.add(new Estate(index, 200));
        }

        places.add(new Hospital(index++));
        i = index;
        for (; index < i + 13; index++) {
            places.add(new Estate(index, 200));
        }

        places.add(new ToolHouse(index++));
        i = index;
        for (; index < i + 6; index++) {
            places.add(new Estate(index, 500));
        }

        places.add(new GiftHouse(index++));
        i = index;
        for (; index < i + 13; index++) {
            places.add(new Estate(index, 300));
        }

        places.add(new Police(index++));
        i = index;
        for (; index < i + 13; index++) {
            places.add(new Estate(index, 300));
        }

        places.add(new MagicHouse(index++));
        i = index;
        for (; index < i + 6; index++) {
            places.add(new Estate(index, 500));
        }

    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Place move(Player player, int steps) {
        int current = player.getCurrentPlace().getPosition();
        for (int i = 0; i < steps; i++) {
            if (items.containsKey(current)) {
                Item item = items.get(current);
                items.remove(current);
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

    public void print() {
        for (int i = 1; i <= 29; i++) {
            print(i);
        }

        System.out.print("\n");
        for (int i = 70; i >= 65; i--) {
            print(i);
            for (int j = 0; j < 27; j++) {
                System.out.print(" ");
            }
            print(100 - i);
            System.out.print("\n");
        }

        for (int i = 64; i >= 36; i--) {
            print(i);
        }
        System.out.print("\n");
    }

    private void print(int position) {
        Player playerAtPosition = players.stream().filter(player -> player.getCurrentPlace().getPosition() == position).findFirst().orElse(null);
        Item itemAtPosition = getItemAt(position);
        if (itemAtPosition != null) {
            getItemAt(position).print();
        } else if (playerAtPosition != null) {
            playerAtPosition.print();
        } else {
            getPlace(position).print();
        }
    }
}
