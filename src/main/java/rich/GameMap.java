package rich;

import rich.Item.Bomb;
import rich.Item.Item;
import rich.Item.RoadBlock;
import rich.place.Hospital;
import rich.place.Place;

import java.util.*;

public class GameMap {
    private List<Place> places = new ArrayList();
    private Map<Integer, Item> items = new HashMap();
    private List<Player> players = new ArrayList();

    public GameMap(Place... places) {
        this.places.addAll(Arrays.asList(places));
    }

    public Place move(Player player, int step) {
        int position = 0;

        Iterator<Map.Entry<Integer, Item>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Item> itemEntry = iterator.next();
            int itemPosition = itemEntry.getKey();
            Item item = itemEntry.getValue();
            int destination = (player.getCurrentPlace().getPosition() + step - 1) % places.size() + 1;
            boolean reach = isInBetween(itemPosition, player.getCurrentPlace().getPosition(), destination);
            if (reach) {
                iterator.remove();
                if (item instanceof RoadBlock) {
                    return getPlace(itemPosition - 1);
                } else if (item instanceof Bomb) {
                    player.burn();
                    return getHospital();
                }
            }
        }

        if (player.getCurrentPlace() != null) {
            position = player.getCurrentPlace().getPosition() - 1;
        }

        position = (position + step) % places.size();
        return getPlace(position);
    }

    private Place getHospital() {
        return places.stream().filter(place -> place instanceof Hospital).findFirst().get();
    }

    private boolean isInBetween(int test, int start, int end) {
        if ((end < test && test < start)
                || (test < start && start < end)
                || (start < end && end < test)) {
            return false;
        }
        return true;
    }

    public Place getPlace(int position) {
        return places.get(position);
    }

    public Item getItemAt(int position) {
        return items.get(position);
    }

    public boolean putItemAt(Item item, int position) {
        if (!items.containsKey(position)) {
            items.put(position, item);
            return true;
        }
        return false;
    }

    public void addPlayers(Player... players) {
        this.players.addAll(Arrays.asList(players));
    }

    public boolean anyPlayerAt(int position) {
        return players.stream().map(p -> p.getCurrentPlace().getPosition()).anyMatch(p -> p == position);
    }

    public int getDistance(Player player, int position) {
        return Math.abs(player.getCurrentPlace().getPosition() - position) % places.size();
    }

    public void clearItems(int position, int steps) {
        Iterator<Map.Entry<Integer, Item>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()) {
            int itemPosition = iterator.next().getKey();
            int destination = (position + steps - 1) % places.size();
            if (isInBetween(itemPosition, position, destination)) {
                iterator.remove();
            }
        }
    }
}
