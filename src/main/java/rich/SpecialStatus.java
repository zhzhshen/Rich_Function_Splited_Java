package rich;

public class SpecialStatus {
    private Player.GameStatus status;
    private int turnsLeft;

    public SpecialStatus(Player.GameStatus status) {
        this.status = status;
        this.turnsLeft = status.getTurns() + 1;
    }

    public Player.GameStatus getStatus() {
        return status;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public int reduceTurnLeft() {
        return turnsLeft--;
    }
}
