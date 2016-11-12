package rich;

import rich.place.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMap {
    private final List<Place> places = new ArrayList();

    public GameMap(Place... places) {
        this.places.addAll(Arrays.asList(places));
    }

    public Place move(Place currentPlace, int step) {
        int position = 0;
        if (currentPlace != null) {
            position = currentPlace.getPosition() - 1;
        }

        position = (position + step) % places.size();
        return places.get(position);
    }

    public Place getPlace(int i) {
        return null;
    }
}
