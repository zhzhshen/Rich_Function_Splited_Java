package rich.command;

import com.sun.tools.javac.util.Pair;
import rich.Player;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Item;
import rich.item.Robot;
import rich.response.Response;

public class QueryCommand implements Command {
    @Override
    public Pair<Player.Status, String> execute(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append("资金:" + (int) player.getBalance() + "元\n");
        sb.append("点数:" + player.getPoint() + "点\n");
        sb.append("地产:空地" + getNumberOfEstate(player, 0) + "处;" +
                "茅屋" + getNumberOfEstate(player, 1) + "处;" +
                "洋房" + getNumberOfEstate(player, 3) + "处;" +
                "摩天楼" + getNumberOfEstate(player, 3) + "处\n");
        sb.append("道具:路障" + getNumberOfItem(player, new Barricade()) + "个;" +
                "炸弹" + getNumberOfItem(player, new Bomb()) + "个;" +
                "机器娃娃" + getNumberOfItem(player, new Robot()) + "个\n");
        return Pair.of(Player.Status.WAIT_FOR_COMMAND, sb.toString());
    }

    private int getNumberOfItem(Player player, Item item) {
        return (int) player.getItems().stream().filter(i -> i.equals(item)).count();
    }

    private int getNumberOfEstate(Player player, int level) {
        return (int) player.getEstates().stream().filter(estate -> estate.getLevel() == level).count();
    }

    @Override
    public Pair<Player.Status, String> respond(Player player, Response response) {
        return response.execute(player);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof QueryCommand;
    }
}
