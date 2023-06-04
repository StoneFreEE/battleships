package battleshipsGUI;

import battleships.*;
import java.util.HashSet;

/**
 * Represents a game board for battleships.
 */
public class Board {

    // the size of the board
    public final static int BOARD_SIZE = 10;
    // 2d array to store cells
    public int[][] cells = new int[BOARD_SIZE][BOARD_SIZE];

    /**
     * Constructs a new board and initializes all cells to the NONE state.
     */
    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[j][i] = States.NONE.ordinal();
            }
        }
    }

    /**
     * Updates the cells of the board based on the given ship object.
     *
     * @param ship the ship object to place on the board.
     */
    public void placeShip(Ship ship) {
        // change from display coordinates to cell coordinates
        // Check if vertical using the origin and end point y
        if (ship.origin.getY() != ship.endPoint.getY()) {
            // Check if going up or down
            if (ship.origin.getY() < ship.endPoint.getY()) {
                for (int j = ship.origin.getY(); j < ship.endPoint.getY() + 1; j++) {
                    cells[j][ship.origin.getX()] = States.SHIP.ordinal();
                }
            } else {
                for (int j = ship.endPoint.getY(); j < ship.origin.getY() + 1; j++) {
                    cells[j][ship.origin.getX()] = States.SHIP.ordinal();
                }
            }
        }
        // Check if horizontla using origin and end point BOARD_SIZE
        if (ship.origin.getX() != ship.endPoint.getX()) {
            // Check if going left or right
            if (ship.origin.getX() < ship.endPoint.getX()) {
                for (int j = ship.origin.getX(); j < ship.endPoint.getX() + 1; j++) {
                    cells[ship.origin.getY()][j] = States.SHIP.ordinal();
                }
            } else {
                for (int j = ship.endPoint.getX(); j < ship.origin.getX() + 1; j++) {
                    cells[ship.origin.getY()][j] = States.SHIP.ordinal();
                }
            }
        }
    }

    /**
     * Finds all possible end points for placing a ship on the board without overlapping with other ships.
     *
     * @param ship the ship object for which to find possible end points.
     * @return a HashSet of all possible end points.
     */
    public HashSet<Coordinate> checkPossible(Ship ship) {
        HashSet<Coordinate> possiblePoints = new HashSet<>();
        // If statements checks possible points within boundaries
        // For loop checks for already existing ships on board
        boolean place = true;
        if (!(ship.origin.getX() - (ship.length - 1) < 0)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[ship.origin.getY()][ship.origin.getX() - i] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Coordinate(ship.origin.getX() - (ship.length - 1), ship.origin.getY()));
            }
            place = true;
        }
        if (!(ship.origin.getX() + (ship.length - 1) > Board.BOARD_SIZE - 1)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[ship.origin.getY()][ship.origin.getX() + i] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Coordinate(ship.origin.getX() + (ship.length - 1), ship.origin.getY()));
            }
            place = true;
        }
        if (!(ship.origin.getY() - (ship.length - 1) < 0)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[ship.origin.getY() - i][ship.origin.getX()] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Coordinate(ship.origin.getX(), ship.origin.getY() - (ship.length - 1)));
            }
            place = true;
        }
        if (!(ship.origin.getY() + (ship.length - 1) > Board.BOARD_SIZE - 1)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[ship.origin.getY() + i][ship.origin.getX()] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Coordinate(ship.origin.getX(), ship.origin.getY() + (ship.length - 1)));
            }
        }

        return possiblePoints;
    }

    /**
     * Parses a string representation of a point, such as "A1", into a Coordinate object.
     *
     * @param text the string representation of the point
     * @return a Coordinate object representing the parsed point, or null if the input is invalid
     */
    public Coordinate parsePoint(String text) {
        // Check valid lengths
        if (text.length() > 3 || text.length() < 2) {
            return null;
        }
        // Convert a10 to A etc
        char ch = Character.toUpperCase(text.charAt(0));
        try {
            // Get last digits of text etc. a10 -> 10
            int digit = Integer.parseInt(text.substring(1));
            // Check within boundaries and input is an alphabet
            if (!(Character.isAlphabetic(ch)) || digit <= 0 || digit > BOARD_SIZE || ch > (BOARD_SIZE - 1 + 'A')) {
                return null;
            }
            Coordinate newPoint = new Coordinate(digit - 1, ch - 'A');
            return newPoint;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Prints the current state of the board, with each cell represented by a symbol
     * indicating its state.
     */
    public void printBoard() {
        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= Board.BOARD_SIZE; i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("\n");

        // print board
        char letter = 'A';
        for (int[] row : this.cells) {
            // print letter coordinates
            System.out.print(letter++ + "   ");

            for (int element : row) {
                if (element == States.MISS.ordinal()) {
                    System.out.print(" # ");
                } else if (element == States.HIT.ordinal()) {
                    System.out.print(" X ");
                } else if (element == States.SHIP.ordinal()) {
                    System.out.print(" O ");
                } else {
                    System.out.print(" . ");
                }

            }
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Determines whether a given point is free (not occupied by a ship) on the board.
     *
     * @param point the point to check
     * @return true if the point is free, false otherwise
     */
    public boolean isFree(Coordinate point) {
        return this.cells[point.getY()][point.getX()] != States.SHIP.ordinal();
    }
    
    /**
     * Determines whether a given point represents a hit on a ship on the board.
     *
     * @param point the point to check
     * @return true if the point represents a hit, false otherwise
     */
    public boolean isHit(Coordinate point) {
        return this.cells[point.getY()][point.getX()] == States.HIT.ordinal();
    }
    
    /**
     * Determines whether a given point represents a miss on the board.
     *
     * @param point the point to check
     * @return true if the point represents a miss, false otherwise
     */
    public boolean isMiss(Coordinate point) {
        return this.cells[point.getY()][point.getX()] == States.MISS.ordinal();
    }
    
    /**
     * Determines whether a given ship is sunk on the board, based on the state of its cells.
     *
     * @param ship the ship to check
     * @return true if the ship is sunk, false otherwise
     */
    public boolean isSunk(Ship ship) {
        int sunkCellCount = 0;
        // if origin point x is same as end point x, ship is vertical 
        // if origin point y is higher than endpoint y, ship is oriented top to bottom
        if (ship.origin.getX() == ship.endPoint.getX()) {
            for (int i = 0; i < ship.length; i++) {
                // check if all cells of ship are sunk cells
                if (ship.origin.getY() > ship.endPoint.getY()) {
                    if (this.cells[ship.origin.getY() - i][ship.origin.getX()] == States.HIT.ordinal()) {
                        sunkCellCount++;
                    }
                } else {
                    if (this.cells[ship.origin.getY() + i][ship.origin.getX()] == States.HIT.ordinal()) {
                        sunkCellCount++;
                    }
                }

            }
        } else {
            // if ship is horizontal
            for (int i = 0; i < ship.length; i++) {
                // check if all cells of ship are sunk cells
                // if origin x is greater than endpoint x, ship is oriented from right to left
                if (ship.origin.getX() > ship.endPoint.getX()) {
                    if (this.cells[ship.origin.getY()][ship.origin.getX() - i] == States.HIT.ordinal()) {
                        sunkCellCount++;
                    }
                } else {
                    if (this.cells[ship.origin.getY()][ship.origin.getX() + i] == States.HIT.ordinal()) {
                        sunkCellCount++;
                    }
                }

            }
        }
        return (sunkCellCount == ship.length);
    }
    
    /**
     * Determines whether a given point is within the boundaries of the board.
     *
     * @param point the point to check
     * @return true if the point is within the boundaries, false otherwise
     */
    public static boolean isValid(Coordinate point) {
        return (!(point.x > BOARD_SIZE - 1) || !(point.x <= 0) || !(point.y > BOARD_SIZE - 1) || !(point.y <= 0));
    }

    /**
     * Fires a cannon at the given point on the board, marking it as HIT if it is occupied
     * by a ship, or as MISS otherwise.
     *
     * @param point the point to fire at
     */
    public void fireAt(Coordinate point) {
        if (this.cells[point.getY()][point.getX()] == States.SHIP.ordinal()) {
            this.cells[point.getY()][point.getX()] = States.HIT.ordinal();
        } else {
            this.cells[point.getY()][point.getX()] = States.MISS.ordinal();
        }
    }
}
