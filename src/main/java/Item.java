public class Item implements Buyable {
    private final String name;
    private final int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setOwner(Player owner) {

    }

    public Player getOwner() {
        return null;
    }

    public int getUnitPrice() {
        return price;
    }

    public int getLevel() {
        return 0;
    }

    public void setLevel(int level) {
    }

    public boolean isMaxLevel() {
        return true;
    }

    public void action(Player player) {
        if (player.getItems().size() < Player.MAX_ITEM
                && player.reduceMoney(getUnitPrice())) {
            setOwner(player);
            player.addItem(this);
        }
    }
}
