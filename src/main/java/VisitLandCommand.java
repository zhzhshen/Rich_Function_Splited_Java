public class VisitLandCommand implements Command {
    public void action(Place place, Player player) {
        Land land = (Land) place;
        if (land.getOwner()== null) {
            land.sellTo(player);
        } else if (land.getOwner().equals(player)) {
            land.builtBy(player);
        } else {
            if (!land.getOwner().isInHospital()
                    && !land.getOwner().isInPrison()
                    && !player.hasEvisu()) {
                land.charge(player);
            }
        }
    }
}
