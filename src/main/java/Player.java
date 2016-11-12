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
    private int point;

    public Player(GameMap map) {
        this.map = map;
    }

    public ControlStatus getControlStatus() {
        return status;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void gainMoney(double amount) {
        cashBalance += amount;
    }

    public boolean reduceMoney(double amount) {
        if (getCashBalance() >= amount) {
            cashBalance -= amount;
            return true;
        }
        return false;
    }

    public int getPoint() {
        return point;
    }

    public void setPointBalance(int point) {
        this.point = point;
    }

    public void gainPoint(int amount) {
        point += amount;
    }

    public boolean reducePoint(int amount) {
        if (getPoint() >= amount) {
            point -= amount;
            return true;
        }
        return false;
    }

    public ControlStatus roll(Dice dice) {
        currentPlace = map.move(currentPlace, dice.roll());
        if (currentPlace.isInputRequired(this)) {
            status = ControlStatus.WAIT_FOR_INPUT;
        } else {
            currentPlace.action(this, 0);
            status = ControlStatus.TURN_END;
        }
        return status;
    }

    public void sayNo() {
        status = ControlStatus.TURN_END;
    }

    public void sayYes() {
        currentPlace.action(this, 0);
        status = ControlStatus.TURN_END;
    }

    public void sayYesToByTool(int toolIndex) {
        if (currentPlace instanceof ToolHouse) {
            ToolHouse toolHouse = (ToolHouse) currentPlace;
            toolHouse.action(this, toolIndex);
        }
        status = ControlStatus.WAIT_FOR_INPUT;
    }

    public void chooseGift(int giftIndex) {
        if (currentPlace instanceof GiftHouse) {
            GiftHouse giftHouse = (GiftHouse) currentPlace;
            giftHouse.action(this, giftIndex);
        }
        status = ControlStatus.TURN_END;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
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
