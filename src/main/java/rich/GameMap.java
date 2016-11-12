package rich;

import rich.Item.Item;
import rich.place.Place;

import java.util.*;

public class GameMap {
    private List<Place> places = new ArrayList();
    private Map<Integer, Item> items = new HashMap();
    private List<Player> players = new ArrayList();

    public GameMap(Place... places) {
        this.places.addAll(Arrays.asList(places));
    }

    public Place move(Place currentPlace, int step) {
        int position = 0;
        if (currentPlace != null) {
            position = currentPlace.getPosition() - 1;
        }

        position = (position + step) % places.size();
        return getPlace(position);
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
        return players.stream().map(p->p.getCurrentPlace().getPosition()).anyMatch(p->p==position);
    }

    public int getDistance(Player player, int position) {
        return Math.abs(player.getCurrentPlace().getPosition() - position) % places.size();
    }

    public void clearItems(int position, int steps) {
        Iterator<Map.Entry<Integer, Item>> iterator = items.entrySet().iterator();
        while (iterator.hasNext()) {
            int itemPosition = iterator.next().getKey();
            if (itemPosition > position && itemPosition <= position + steps) {
                iterator.remove();
            }
        }
    }
}
