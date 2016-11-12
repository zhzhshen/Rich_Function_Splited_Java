public class BuyToolCommand implements Command {
    private int toolIndex;

    public BuyToolCommand(int toolIndex) {
        this.toolIndex = toolIndex;
    }

    public void action(Place place, Player player) {
        ToolHouse toolHouse = (ToolHouse) place;
        Item item = toolHouse.getItems().get(toolIndex - 1);
        if (player.getItems().size() < Player.MAX_ITEM
                && player.reducePoint(item.getPrice())) {
            player.addItem(item);
        }
    }
}
