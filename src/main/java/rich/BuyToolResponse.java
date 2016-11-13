package rich;

public class BuyToolResponse {
    static final int PRICE_BARRICADE = 50;
    static final int PRICE_ROBOT = 30;
    private static int PRICE_Bomb = 50;
    public static Response Buy_Barricade = player -> {
        if (player.reducePoint(PRICE_BARRICADE)) {
            player.addItem(new Barricade());
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    public static Response Buy_Robot = player -> {
        if (player.reducePoint(PRICE_ROBOT)) {
            player.addItem(new Robot());
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    public static Response Buy_Bomb  = player -> {
        if (player.reducePoint(PRICE_Bomb)) {
            player.addItem(new Bomb());
        }
        return player.getCurrentPlace().visitedBy(player);
    };
    public static Response No  = player -> Player.Status.TURN_END;;
}
