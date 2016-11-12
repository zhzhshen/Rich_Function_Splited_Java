import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int MAX_ITEM = 10;
    private GameMap map;
    private double cashBalance;
    private ControlStatus status = ControlStatus.WAIT_FOR_INPUT;
    private Place currentPlace;
    private List<Item> items = new ArrayList<>(MAX_ITEM);
    private List<GameStatus> gameStatus = new ArrayList<>();

    public Player(GameMap map) {
        this.map = map;
    }

    public ControlStatus getControlStatus() {
        return status;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public ControlStatus roll(Dice dice) {
        currentPlace = map.move(currentPlace, dice.roll());
        if (currentPlace.isInputRequired(this)) {
            status = ControlStatus.WAIT_FOR_INPUT;
        } else {
            currentPlace.action(this);
            status = ControlStatus.TURN_END;
        }
        return status;
    }

    public void sayYes() {
        this.currentPlace.action(this);
        status = ControlStatus.TURN_END;
    }

    public void sayYesToByTool(int index) {
        if (currentPlace instanceof ToolHouse) {
            ToolHouse toolHouse = (ToolHouse) currentPlace;
            Buyable item = toolHouse.getItem(index);
            item.action(this);
        }
        status = ControlStatus.WAIT_FOR_INPUT;
    }

    protected boolean reduceMoney(double amount) {
        if (getCashBalance() >= amount) {
            cashBalance -= amount;
            return true;
        }
        return false;
    }

    public void sayNo() {
        status = ControlStatus.TURN_END;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void gainMoney(double amount) {
        cashBalance += amount;
    }

    public void burn() {
        gameStatus.add(GameStatus.IN_HOSPITAL);
    }

    public void prisoned() {
        gameStatus.add(GameStatus.IN_PRISON);
    }

    public void evisu() {
        gameStatus.add(GameStatus.HAS_EVISU);
    }

    public boolean isInHospital() {
        return gameStatus.contains(GameStatus.IN_HOSPITAL);
    }

    public boolean isInPrison() {
        return gameStatus.contains(GameStatus.IN_PRISON);
    }

    public boolean hasEvisu() {
        return gameStatus.contains(GameStatus.HAS_EVISU);
    }

    public enum ControlStatus {TURN_END, WAIT_FOR_INPUT}

    public enum GameStatus {IN_PRISON, HAS_EVISU, IN_HOSPITAL}
}
