package battleshipsGUI;

/**
 * This class represents a Ship object in the game Battleships. It has a length,
 * origin point, and end point. The length represents how many spaces the ship 
 * occupies, the origin point represents the starting position of the ship on 
 * the game board, and the end point represents the end position of the ship on 
 * the game board.
 * 
 * @author 64272
 */
public class Ship {

    public int length;
    public Coordinate origin;
    public Coordinate endPoint;

    /**
     * Constructor for creating a Ship object with just a length and origin 
     * point.
     * 
     * @param length the length of the ship
     * @param origin the starting position of the ship on the game board
     */
    public Ship(int length, Coordinate origin) {
        this.length = length;
        this.origin = origin;
        this.endPoint = new Coordinate();
    }

    /**
     * Constructor for creating a Ship object with a length, origin point, and 
     * end point.
     * 
     * @param length the length of the ship
     * @param origin the starting position of the ship on the game board
     * @param end the ending position of the ship on the game board
     */
    public Ship(int length, Coordinate origin, Coordinate end) {
        this.length = length;
        this.origin = origin;
        this.endPoint = end;
    }
}
