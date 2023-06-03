
package battleshipsGUI;

import battleships.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;


public class User extends Player implements Comparable<User>{
    private int score;
    private String name;
    

    public User() {
        super();
        this.score = 500;
        this.name = "";
    }
    
    public User(String name, int score) {
        super();
        this.name = name;
        this.score = score;
    }
    
    public User(String name) {
        super();
        this.name = name;
        this.score = 500;
    }
    
    /**
    * Sets the name of the user
    * 
    * @param name the name to set
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    * Sets the score of the user
    * 
    * @param score the score to set
    */
    public void setScore(int score) {
        this.score = score;
    }
    
    /**
    * Returns the score of the user
    * 
    * @return the score of the user
    */
    public int getScore() {
        return this.score;
    }
    
    /**
    * Returns the name of the user
    * 
    * @return the name of the user
    */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns a string representation of the user
     * 
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return this.name + " " + this.score;
    }
    
    /**
    * Sets the ships of the user
    * 
    * @param ships the ships to set
    */
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }
    
    /**
    * Initializes the board for the user by taking input from the console
    * 
    * @param shipLengths the array of lengths of the ships
    */
    @Override
    public void initBoard( int[] shipLengths) {

        Scanner scanner = new Scanner(System.in);

        this.board = new Board();

        int nShips = shipLengths.length;

        for (int i = 0; i < nShips; i++) {
            this.board.printBoard();
            System.out.println("Placing ship of length " + shipLengths[i]);
            HashSet<Point> possiblePoints = new HashSet<>();
            Point userOrigin = new Point();

            do {
                System.out.println("Pick an origin (e.g C4): ");
                String originResponse = scanner.nextLine();
                userOrigin = board.parsePoint(originResponse);
                if (userOrigin != null) {
                    Ship ship = new Ship(shipLengths[i], userOrigin);
                    possiblePoints = board.checkPossible(ship);
                    if (possiblePoints.isEmpty()) {
                        System.out.println("No possible end points found");
                        System.out.println("Choose another origin");
                    }
                } else {
                    System.out.println("Invalid origin");
                }
                System.out.println("");
            } while (possiblePoints.isEmpty() || !board.isFree(userOrigin) || userOrigin == null);

            Point userEnd = new Point();
            do {
                System.out.println("Pick an end point: ");
                // Print possible points
                for (Point point : possiblePoints) {
                    char letter = 'A';
                    letter += (char) (int) point.getY();
                    System.out.println("- " + letter + "" + (point.getX() + 1));
                }
                String endResponse = scanner.nextLine();
                userEnd = this.board.parsePoint(endResponse);
                if (userEnd != null) {
                    if (contains(possiblePoints, userEnd)) {
                        Ship newShip = new Ship(shipLengths[i], userOrigin, userEnd);
                        this.ships.add(newShip);
                        this.board.placeShip(newShip);
                    } 
                    else {
                        System.out.println("Invalid end point"); 
                    }
                }
                else {
                    System.out.println("Invalid end point");
                }
                System.out.println("");
            } while (!contains(possiblePoints, userEnd));
            this.setShips(ships);
        }
    }
    
    /**
     * Checks if a given point is contained in a HashSet of Points.
     *
     * @param points The HashSet of Points to check.
     * @param end The Point to check for.
     * @return true if the Point is contained in the HashSet, false otherwise.
     */
    public boolean contains(HashSet<Point> points, Point end) {
        if (end != null) {
            for (Point point : points) {
                if (point.equals(end)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Initializes the game board with a set of ships.
     *
     * @param shipLengths An array of ship lengths.
     */
    public void initLoadFile(int[] shipLengths) {
        this.board = new Board();

        for (Ship ship : this.ships) {
            this.board.placeShip(ship);
        }
    }
        
    /**
    * Checks if the user has lost the game by calculating the number of sunk ships
    * @return True if all ships are sunk, False otherwise
    */
    @Override
    public boolean checkLose() {
        this.shipsSunk = 0;
        for (Ship ship : this.ships) {
            this.shipsSunk += this.board.isSunk(ship) ? 1 : 0;
        }
        return (this.shipsSunk == this.ships.size());
    }
    
    
    /**
     * Compares this User object with another User object by score and name.
     * @param o The User object to compare with
     * @return A negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(User o) {
        // if the score are not equal
        if (this.score != o.score) {
            return this.score - o.score;
        }
        else {
            // we compare name values
            // if the scores are equal
            return this.name.toUpperCase().compareTo(o.name.toUpperCase());
        }
    }
    
    
    /**
     * Compares this User object with another Object to check if they are equal
     * @param o The Object to compare with
     * @return True if the Objects are equal, False otherwise
     */
    @Override
    public boolean equals(Object o) {
        User user = (User)o;
        return user.name.toUpperCase().equals(this.name.toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.score;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
