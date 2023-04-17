package battleships;

/**
 *
 * @author 64272
 */
public class BattleShips {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Board board = new Board(10);

        int[] shipLengths = {2, 3, 3, 4, 5};

        GameLogic game = new GameLogic(new Player(), new AIEnemy());

        // printTitle();
        game.startGame(10, shipLengths);
    }

    private static void printTitle() {
        System.out.println("__________    ________________.____     ___________ _________ ___ ___ ._____________  _________\n"
                + "\\______   \\  /  _  \\__    ___/|    |    \\_   _____//   _____//   |   \\|   \\______   \\/   _____/\n"
                + " |    |  _/ /  /_\\  \\|    |   |    |     |    __)_ \\_____  \\/    ~    \\   ||     ___/\\_____  \\ \n"
                + " |    |   \\/    |    \\    |   |    |___  |        \\/        \\    Y    /   ||    |    /        \\\n"
                + " |______  /\\____|__  /____|   |_______ \\/_______  /_______  /\\___|_  /|___||____|   /_______  /\n"
                + "        \\/         \\/                 \\/        \\/        \\/       \\/                       \\/\n ");
    }

}
