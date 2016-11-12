public class SellLandCommand implements Command {
    public void action(Place place, Player player) {
        Land land = (Land) place;
        land.setLevel(0);
        land.setOwner(null);
        player.gainMoney(land.getPrice());
    }
}
