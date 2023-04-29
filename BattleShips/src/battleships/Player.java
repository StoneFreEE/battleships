package battleships;

import java.util.ArrayList;

/**
 * The abstract Player class is the parent class for the User and Enemy classes.
 * It contains common properties and methods that both User and Enemy will use.
 * 
 * @author oliver
 */
public abstract class Player {
    Board board;
    int shipsSunk;
    ArrayList<Ship> ships;
    
     /**
     * Constructor for Player.
     * Initializes shipsSunk to 0.
     */
    public Player() {
        this.ships = new ArrayList<Ship>();
        this.shipsSunk = 0;
    }
    
     /**
     * Abstract method that will be implemented by User and Enemy.
     * Used to initialize the board with ships.
     * 
     * @param shipLengths An array of integers representing the lengths of the 
     * ships to be placed on the board.
     */
    public abstract void initBoard( int[] shipLengths);
    
    /**
     * Abstract method that will be implemented by User and Enemy.
     * Used to check if the player has lost the game.
     * 
     * @return true if the player has lost, false if not.
     */
    public abstract boolean checkLose();
    
    /**
     * Method to translate a Point object into a string representation of the 
     * coordinates.
     * 
     * @param point The Point object to be translated.
     * @return A string representing the coordinates of the Point.
     */
    public String translatePoint(Point point) {
        String out = "";
        out += (char)('A' + point.getY());
        out += point.getX() + 1;

        return out;
    }
}
