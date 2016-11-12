import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int MAX_ITEM = 10;
    private GameMap map;
    private int cashBalance;
    private Status status = Status.WAIT_FOR_INPUT;
    private Place currentPlace;
    private List<Item> items = new ArrayList<>(MAX_ITEM);

    public Player(GameMap map) {
        this.map = map;
    }

    public Status getStatus() {
        return status;
    }

    public int getCashBalance() {
        return cashBalance;
    }

    public Status roll(Dice dice) {
        currentPlace = map.move(currentPlace, dice.roll());
        status = currentPlace.isInputRequired() ? Status.WAIT_FOR_INPUT : Status.TURN_END;
        return status;
    }

    public void sayYes() {
        if (currentPlace instanceof Buyable) {
            ((Buyable) this.currentPlace).action(this);
        }
        status = Status.TURN_END;
    }

    public void sayYesToByTool(int index) {
        if (currentPlace instanceof ToolHouse) {
            ToolHouse toolHouse = (ToolHouse) currentPlace;
            Buyable item = toolHouse.getItem(index);
            item.action(this);
        }
        status = Status.WAIT_FOR_INPUT;
    }

    protected boolean reduceMoney(int amount) {
        if (getCashBalance() >= amount) {
            cashBalance -= amount;
            return true;
        }
        return false;
    }

    public void sayNo() {
        status = Status.TURN_END;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public enum Status {TURN_END, WAIT_FOR_INPUT}
}
