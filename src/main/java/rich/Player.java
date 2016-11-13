package rich;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Status status = Status.WAIT_FOR_COMMAND;
    private Command command;
    private Place currentPlace;
    private GameMap map;
    private double balance;
    private List<Place> lands;
    private List<SpecialStatus> specialStatus = new ArrayList();
    private int point;
    private List<Item> items = new ArrayList();

    public Player(GameMap map, double balance, int point) {
        this.map = map;
        this.currentPlace = map.getStartingPoint();
        this.balance = balance;
        this.point = point;
        lands = new ArrayList();
        map.addPlayer(this);
    }

    public double getBalance() {
        return balance;
    }

    public Status getStatus() {
        return status;
    }

    public void execute(Command command) {
        this.command = command;
        status = this.command.execute(this);
    }

    public void respond(Response response) {
        status = command.respond(this, response);
    }

    public Place getCurrentPlace() {
        return currentPlace;
    }

    public GameMap getMap() {
        return map;
    }

    public void moveToPlace(Place newPlace) {
        currentPlace = newPlace;
    }

    public void buy() {
        if (currentPlace instanceof Estate) {
            Estate estate = (Estate) currentPlace;
            if (reduceMoney(estate.getPrice())) {
                estate.sellTo(this);
                lands.add(estate);
            }
        }
    }

    public void build() {
        if (currentPlace instanceof Estate) {
            Estate estate = (Estate) currentPlace;
            if (!estate.isMaxLevel()
                    && reduceMoney(estate.getPrice())) {
                estate.build();
            }
        }
    }

    public boolean reduceMoney(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void gainMoney(double amount) {
        balance += amount;
    }

    public void burn() {
        specialStatus.add(SpecialStatus.IN_HOSPITAL);
    }

    public boolean isInHospital() {
        return specialStatus.contains(SpecialStatus.IN_HOSPITAL);
    }

    public void prisoned() {
        specialStatus.add(SpecialStatus.IN_PRISON);
    }

    public boolean isInPrison() {
        return specialStatus.contains(SpecialStatus.IN_PRISON);
    }

    public void evisu() {
        specialStatus.add(SpecialStatus.HAS_EVISU);
    }

    public boolean hasEvisu() {
        return specialStatus.contains(SpecialStatus.HAS_EVISU);
    }

    public int getPoint() {
        return point;
    }

    public void gainPoint(int amount) {
        point += amount;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean reducePoint(int amount) {
        if (point >= amount) {
            point -= amount;
            return true;
        }
        return false;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void use(Item item, int steps) {
        item.use(this, steps);
    }

    public enum Status {WAIT_FOR_RESPONSE, TURN_END, WAIT_FOR_COMMAND}

    public enum SpecialStatus {IN_PRISON, HAS_EVISU, IN_HOSPITAL}
}
