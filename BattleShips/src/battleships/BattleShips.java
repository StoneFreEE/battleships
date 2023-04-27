package battleships;

import java.awt.Point;
import java.io.IOException;
import java.util.Iterator;

/**
 *
 * @author 64272
 */
public class BattleShips {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Board board = new Board(10);

        final int[] shipLengths = {2, 3, 3, 4, 5};
        
        boolean keepGoing = true;
        
        boolean playerWin = false;
        
        GameLogic game = new GameLogic(new User(), new AIEnemy(), new Database());
        game.load();
        
        printTitle();
        game.startGame(10, shipLengths);
        
        // keep taking turns until either ai or player wins
        while(keepGoing){
            game.takeTurn();
            
            if (game.checkEnemyWin()){
                keepGoing = false;
                playerWin = false;
            } else if (game.checkPlayerWin()){
                keepGoing = false;
                playerWin = true;
            }
        }
        if (playerWin){
            System.out.println("Congrats! Player wins!"); 
        } else{
            System.out.println("Damn. Player loses!");
        }
        game.saveFile(game.user);
        System.out.println("");
        System.out.println("LeaderBoard: ");
        Iterator it = game.database.getUsers().descendingIterator();
                while(it.hasNext()) {
                    System.out.println(it.next());
                }
        
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
