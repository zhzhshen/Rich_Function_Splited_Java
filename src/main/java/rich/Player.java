package rich;

import rich.command.Command;
import rich.io.Color;
import rich.item.Item;
import rich.place.Estate;
import rich.place.Place;
import rich.response.Response;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private Status status = Status.WAIT_FOR_COMMAND;
    private Command command;
    private Place currentPlace;
    private GameMap map;
    private double balance;
    private List<Estate> estates;
    private List<SpecialStatus> specialStatus = new ArrayList();
    private int point;
    private List<Item> items = new ArrayList();
    private int skipTurns;
    private String message;
    private String color;
    private String legend;

    public Player(GameMap map, double balance, int point) {
        this.map = map;
        this.currentPlace = map.getStartingPoint();
        this.balance = balance;
        this.point = point;
        this.name = "";
        estates = new ArrayList();
        map.addPlayer(this);
    }

    public Player(GameMap map, String name, double balance, int point) {
        this.name = name;
        this.map = map;
        this.currentPlace = map.getStartingPoint();
        this.balance = balance;
        this.point = point;
        estates = new ArrayList();
        map.addPlayer(this);
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public boolean buy() {
        if (currentPlace instanceof Estate) {
            Estate estate = (Estate) currentPlace;
            if (reduceMoney(estate.getPrice())) {
                estate.sellTo(this);
                estates.add(estate);
                return true;
            }
        }
        return false;
    }

    public boolean build() {
        if (currentPlace instanceof Estate) {
            Estate estate = (Estate) currentPlace;
            if (!estate.isMaxLevel()
                    && reduceMoney(estate.getPrice())) {
                estate.build();
                return true;
            }
        }
        return false;
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
        skipTurns = 3;

    }

    public boolean isInHospital() {
        return specialStatus.contains(SpecialStatus.IN_HOSPITAL);
    }

    public void prisoned() {
        specialStatus.add(SpecialStatus.IN_PRISON);
        skipTurns = 2;
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

    public void sell(int position) {
        Place place = map.getPlace(position);
        if (place != null && place instanceof Estate) {
            Estate estate = (Estate) place;
            if (this.equals(estate.getOwner())) {
                gainMoney(estate.sell());
            }
        }
    }

    public void sellItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            gainPoint(item.getPoint());
        }
    }

    public boolean activate() {
        if (isInHospital() || isInPrison()) {
            if (skipTurns == 0) {
                status = Status.WAIT_FOR_COMMAND;
                return true;
            }
            skipTurns--;
            status = Status.TURN_END;
            return false;
        }
        status = Status.WAIT_FOR_COMMAND;
        return true;
    }

    public void remove() {
        for (Estate estate : estates) {
            estate.sell();
        }
        status = Status.GAME_OVER;
    }

    public boolean isGameOver() {
        return status == Status.GAME_OVER;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void print() {
        System.out.print(color + legend + Color.ANSI_RESET);
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public enum Status {WAIT_FOR_RESPONSE, TURN_END, GAME_OVER, WAIT_FOR_COMMAND}

    public enum SpecialStatus {IN_PRISON, HAS_EVISU, IN_HOSPITAL}
}
