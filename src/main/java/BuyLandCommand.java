public class BuyLandCommand implements Command {
    public void action(Place place, Player player) {
        Land land = (Land) place;
        if (land.getOwner()== null) {
            land.sellTo(player);
        } else if (land.getOwner().equals(player)) {
            land.builtBy(player);
        }
    }
}
