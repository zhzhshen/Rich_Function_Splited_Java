public interface GameMap {
    Place move(Place currentPlace, int roll);

    Place getPlace(int i);
}
