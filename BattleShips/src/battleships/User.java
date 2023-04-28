
package battleships;

import java.util.ArrayList;
import java.util.HashSet;
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
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + " " + this.score;
    }
    
    public void initBoard( int[] shipLengths, ArrayList<Ship> ships) {

        Scanner scanner = new Scanner(System.in);

        this.board = new Board();

        int nShips = shipLengths.length;

        for (int i = 0; i < nShips; i++) {
            this.board.printBoard();
            System.out.println("Placing ship of length " + shipLengths[i]);
            HashSet<Point> possiblePoints = new HashSet<Point>();
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
                userEnd = board.parsePoint(endResponse);
                if (contains(possiblePoints, userEnd)) {
                    Ship newShip = new Ship(shipLengths[i], userOrigin, userEnd);
                    ships.add(newShip);
                    board.placeShip(newShip);
                } else {
                    System.out.println("Invalid end point");
                }
            } while (!contains(possiblePoints, userEnd));
        }
    }
    
    public boolean contains(HashSet<Point> points, Point end) {
        for (Point point : points) {
            if (point.equals(end)) {
                return true;
            }
        }
        return false;
    }
    
    public void initLoadFile(int[] shipLengths, ArrayList<Ship> ships) {
        this.ships = ships;
        this.board = new Board();

        for (Ship ship : this.ships) {
            this.board.placeShip(ship);
        }
    }
    
    @Override
    public boolean checkLose() {
        this.shipsSunk = 0;
        for (Ship ship : this.ships) {
            this.shipsSunk += this.board.isSunk(ship) ? 1 : 0;
        }
        return (this.shipsSunk == this.ships.size());
    }

    @Override
    public int compareTo(User o) {
        // if the score are not equal
        if (this.score != o.score) {
            return this.score - o.score;
        }
        else {
            // we compare name values
            // if the scores are equal
            return this.name.compareTo(o.name);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        User user = (User)o;
        if (user.name.equals(this.name)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void initBoard( int[] shipLengths) {
    }
}
