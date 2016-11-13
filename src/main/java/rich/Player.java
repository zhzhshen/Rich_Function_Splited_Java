package rich;

import rich.Item.Item;
import rich.command.*;
import rich.place.Land;
import rich.place.Place;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public static final int MAX_ITEM = 10;
    private GameMap map;
    private double cashBalance;
    private int point;
    private Place currentPlace;
    private ControlStatus status = Player.ControlStatus.INACTIVE;
    private List<SpecialStatus> gameStatus = new ArrayList<>();
    private List<Item> items = new ArrayList<>(MAX_ITEM);
    private List<Land> lands = new ArrayList<>();

    public Player(GameMap map, double cashBalance, int point) {
        this.currentPlace = map.getPlace(0);
        this.map = map;
        this.cashBalance = cashBalance;
        this.point = point;
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
        currentPlace = map.move(this, dice.roll());
        if (currentPlace.isInputRequired(this)) {
            status = Player.ControlStatus.WAIT_FOR_RESPOND;
        } else {
            respond(new VisitPlaceCommand());
        }
        return status;
    }

    public void respond(Command command) {
        command.action(map, this);
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    public List<Land> getLands() {
        return lands;
    }

    public void gainLand(Land land) {
        lands.add(land);
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void burn() {
        gameStatus.add(new SpecialStatus(GameStatus.IN_HOSPITAL));
    }

    public void prisoned() {
        gameStatus.add(new SpecialStatus(GameStatus.IN_PRISON));
    }

    public void evisu() {
        gameStatus.add(new SpecialStatus(GameStatus.HAS_EVISU));
    }

    public boolean isInHospital() {
        return gameStatus.stream().anyMatch(status -> status.getStatus().equals(GameStatus.IN_HOSPITAL));
    }

    public boolean isInPrison() {
        return gameStatus.stream().anyMatch(status -> status.getStatus().equals(GameStatus.IN_PRISON));
    }

    public boolean hasEvisu() {
        return gameStatus.stream().anyMatch(status -> status.getStatus().equals(GameStatus.HAS_EVISU));
    }

    public boolean activate() {
        status = Player.ControlStatus.WAIT_FOR_COMMAND;
        if (isInHospital() || isInPrison()) {
            SpecialStatus specialStatus = gameStatus.stream()
                    .filter(status -> status.getStatus().equals(GameStatus.IN_HOSPITAL)
                            || status.getStatus().equals(GameStatus.IN_PRISON))
                    .findFirst().get();
            specialStatus.reduceTurnLeft();
            if (specialStatus.getTurnsLeft() == 0) {
                gameStatus.remove(specialStatus);
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    public void deactivate() {
        status = Player.ControlStatus.INACTIVE;
    }

    public int getSkipTurns() {
        return gameStatus.stream()
                .filter(status -> status.getStatus().equals(GameStatus.IN_HOSPITAL)
                        || status.getStatus().equals(GameStatus.IN_PRISON))
                .findFirst()
                .map(status -> status.getTurnsLeft())
                .orElse(0);
    }

    public boolean removeItem(Item item) {
        return getItems().remove(item);
    }

    public void setStatus(ControlStatus status) {
        this.status = status;
    }

    public enum ControlStatus {TURN_END, WAIT_FOR_COMMAND, WAIT_FOR_RESPOND, INACTIVE}

    public enum GameStatus {
        IN_PRISON(2), HAS_EVISU(5), IN_HOSPITAL(3);

        private int turns;

        GameStatus(int turns) {
            this.turns = turns;
        }

        public int getTurns() {
            return turns;
        }
    }

}
