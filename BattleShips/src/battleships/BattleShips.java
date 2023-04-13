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
        // TODO code application logic here
        Board board = new Board(10, 10);
        BoardDisplay boardDisplay = new BoardDisplay(board);
        
        boardDisplay.printBoard();
        
        Ship ship = new Ship(3, 1, 1, false);
        
        board.placeShip(ship);
        
        boardDisplay.printBoard();
    }
    
}