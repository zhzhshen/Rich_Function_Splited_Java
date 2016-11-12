public class VisitPlaceCommand implements Command {
    public void action(Place place, Player player) {
        if (place instanceof Land) {
            Land land = (Land) place;
            if (!land.getOwner().isInHospital()
                    && !land.getOwner().isInPrison()
                    && !player.hasEvisu()) {
                land.charge(player);
            }
        }

        if (place instanceof Police) {
            player.prisoned();
        }
    }
}
