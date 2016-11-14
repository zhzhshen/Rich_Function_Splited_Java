package rich;

import rich.command.Command;
import rich.io.Color;
import rich.io.CommandParser;
import rich.io.ResponseParser;
import rich.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rich World!");

        System.out.println("请选择玩家初始资金 范围1000～50000（默认10000)");
        double balance = Double.valueOf(scanner.nextLine());

        System.out.println("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.小丹尼; 4.金贝贝):");
        String playerStrings = scanner.nextLine();
        GameMap map = new GameMap();
        map.init();

        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerStrings.length(); i++) {
            Player player = null;
            int playerIndex = Integer.valueOf(playerStrings.substring(i, i + 1));
            switch (playerIndex) {
                case 1:
                    player = new Player(map, "钱夫人", balance, 0);
                    player.setColor(Color.ANSI_RED);
                    player.setLegend("Q");
                    break;
                case 2:
                    player = new Player(map, "阿土伯", balance, 0);
                    player.setColor(Color.ANSI_YELLOW);
                    player.setLegend("A");
                    break;
                case 3:
                    player = new Player(map, "小丹尼", balance, 0);
                    player.setColor(Color.ANSI_BLUE);
                    player.setLegend("X");
                    break;
                case 4:
                    player = new Player(map, "金贝贝", balance, 0);
                    player.setColor(Color.ANSI_GREEN);
                    player.setLegend("J");
                    break;
            }
            players.add(player);
        }

        Game game = new Game(map, players.toArray(new Player[players.size()]));

        while (true) {
            game.startTurn();
            map.print();

            while (!game.getActivePlayer().getStatus().equals(Player.Status.TURN_END)) {
                Player currentPlayer = game.getActivePlayer();
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
                        currentPlayer.respond(response);
                        break;
                }

                if (game.isGameOver()) {
                    System.out.println("游戏结束!恭喜" + players.get(0).getName() + "获得了胜利");
                    System.exit(0);
                }
            }

            game.endTurn();
        }
    }
}
