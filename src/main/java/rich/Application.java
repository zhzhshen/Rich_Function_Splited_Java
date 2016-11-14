package rich;

import rich.command.Command;
import rich.io.Color;
import rich.io.CommandParser;
import rich.io.ResponseParser;
import rich.item.Barricade;
import rich.item.Bomb;
import rich.item.Robot;
import rich.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rich World!");

        double balance = 10000;
        do {
            System.out.println("请选择玩家初始资金 范围1000～50000（默认10000)");
            try {
                balance = Double.valueOf(scanner.nextLine());
            } catch (Exception e) {
                continue;
            }
        } while (balance < 1000 || balance > 50000);

        GameMap map = new GameMap();
        List<Player> players = new ArrayList<>();
        map.init();

        boolean valid = false;
        do {
            System.out.println("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.小丹尼; 4.金贝贝):");
            String playerStrings = scanner.nextLine();
            if (playerStrings.isEmpty()) continue;
            valid = true;
            players.clear();
            for (int i = 0; i < playerStrings.length(); i++) {
                Player player;
                int playerIndex = Integer.valueOf(playerStrings.substring(i, i + 1));
                String name = null;
                String legend = null;
                String color = null;
                switch (playerIndex) {
                    case 1:
                        name = "钱夫人";
                        legend = "Q";
                        color = Color.ANSI_RED;
                        break;
                    case 2:
                        name = "阿土伯";
                        legend = "A";
                        color = Color.ANSI_YELLOW;
                        break;
                    case 3:
                        name = "小丹尼";
                        legend = "X";
                        color = Color.ANSI_BLUE;
                        break;
                    case 4:
                        name = "金贝贝";
                        legend = "J";
                        color = Color.ANSI_GREEN;
                        break;
                    default:
                        valid = false;
                        break;
                }

                if (valid) {
                    player = new Player(map, name, balance, 100);
                    player.setColor(color);
                    player.setLegend(legend);
                    player.addItem(new Bomb());
                    player.addItem(new Barricade());
                    player.addItem(new Robot());
                    players.add(player);
                }
            }
        } while (!valid);

        Game game = new Game(map, players.toArray(new Player[players.size()]));

        while (true) {
            game.startTurn();

            while (!game.getActivePlayer().getStatus().equals(Player.Status.TURN_END)) {
                Player currentPlayer = game.getActivePlayer();
                map.print();
                switch (game.getActivePlayer().getStatus()) {
                    case WAIT_FOR_COMMAND:
                        System.out.print(game.getActivePlayer().getName() + ">");
                        String commandString = scanner.nextLine();
                        Command command = CommandParser.parse(commandString);
                        if (command == null) break;
                        System.out.println(currentPlayer.execute(command));
                        break;
                    case WAIT_FOR_RESPONSE:
                        String responseString = scanner.nextLine();
                        Response response = ResponseParser.parse(currentPlayer, responseString);
                        if (response == null) break;
                        System.out.println(currentPlayer.respond(response));
                        break;
                }

                if (game.isGameOver()) {
                    System.out.println("游戏结束!恭喜" + game.getWinner().getName() + "获得了胜利");
                    System.exit(0);
                }
            }

            game.endTurn();
        }
    }
}
