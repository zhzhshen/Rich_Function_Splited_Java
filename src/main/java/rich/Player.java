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

    public Player(GameMap map, double balance) {
        this.map = map;
        this.balance = balance;
        lands = new ArrayList();
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
        if (reduceMoney(currentPlace.getPrice())) {
            currentPlace.sellTo(this);
            lands.add(currentPlace);
        }
    }

    public void build() {
        if (!currentPlace.isMaxLevel()
                && reduceMoney(currentPlace.getPrice())) {
            currentPlace.build();
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

    public enum Status {WAIT_FOR_RESPONSE, TURN_END, WAIT_FOR_COMMAND}

    public enum SpecialStatus {IN_PRISON, HAS_EVISU, IN_HOSPITAL}
}
