package battleships;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author 64272
 */
public class BattleShips {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TODO code application logic here
        Board board = new Board(10, 10);
        BoardDisplay boardDisplay = new BoardDisplay(board);
        printTitle();
        int[] shipLengths = {2, 3, 3, 4, 5};
        int nShips = shipLengths.length;
        
        for (int i = 0; i < nShips; i++) {
            boardDisplay.printBoard();
            System.out.println("Placing ship of length " + shipLengths[i]);
            HashSet<Point> possiblePoints = new HashSet<Point>();
            Point userOrigin = new Point();
            
            do {
                System.out.println("Pick an origin (e.g C4): ");
                String originResponse = scanner.nextLine();
                userOrigin = board.parsePoint(originResponse);
                if (userOrigin != null) {
                    Ship ship = new Ship(shipLengths[i], userOrigin);
                    possiblePoints = board.checkPossible(ship.origin, ship);
                    if (possiblePoints.isEmpty()) {
                        System.out.println("No possible end points found");
                        System.out.println("Choose another origin");
                    } 
                }
                else {
                    System.out.println("Invalid origin");
                }
                System.out.println("");
            }  while (possiblePoints.isEmpty() || !board.isFree(userOrigin) || userOrigin == null);
            
            Point userEnd = new Point();
            do {
                System.out.println("Pick an end point: ");
                // Print possible points
                for (Point point : possiblePoints) {
                    char letter = 'A';
                    letter += (char)(int)point.getY();
                    System.out.println("- " + letter + "" + ((int)point.getX() + 1));
                }
                String endResponse = scanner.nextLine();
                userEnd = board.parsePoint(endResponse);
                if (possiblePoints.contains(userEnd) && userEnd != null) {
                    Ship newShip = new Ship(shipLengths[i], userOrigin, userEnd);
                    board.placeShip(newShip);
                }
                else {
                    System.out.println("Invalid end point");
                }
            } while (!possiblePoints.contains(userEnd));
        }
        
        
        boardDisplay.printBoard();
    }
    
    private static void printTitle() {
        System.out.println("__________    ________________.____     ___________ _________ ___ ___ ._____________  _________\n" +
"\\______   \\  /  _  \\__    ___/|    |    \\_   _____//   _____//   |   \\|   \\______   \\/   _____/\n" +
" |    |  _/ /  /_\\  \\|    |   |    |     |    __)_ \\_____  \\/    ~    \\   ||     ___/\\_____  \\ \n" +
" |    |   \\/    |    \\    |   |    |___  |        \\/        \\    Y    /   ||    |    /        \\\n" +
" |______  /\\____|__  /____|   |_______ \\/_______  /_______  /\\___|_  /|___||____|   /_______  /\n" +
"        \\/         \\/                 \\/        \\/        \\/       \\/                       \\/\n ");
    }
    
}