public class SellItemCommand implements Command {
    private int itemIndex;

    public SellItemCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void action(Place place, Player player) {
        Item item = Items.getItem(itemIndex);
        if (player.getItems().remove(item)) {
            player.gainPoint(item.getPrice());
        }
    }
}
