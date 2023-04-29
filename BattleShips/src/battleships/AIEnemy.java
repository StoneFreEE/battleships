package battleships;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AIEnemy extends Player {
        
    Random rand = new Random();

    /**
     * Constructor for the AIEnemy class.
     * It calls the constructor of its superclass Player.
     */
    public AIEnemy() {
        super();
    }

    /**
     * Method to randomly place ships on the board for the AIEnemy player.
     * @param shipLengths an array of integers representing the length of ships to be placed.
     */
    @Override
    public void initBoard( int[] shipLengths) {
        ships = new ArrayList<>();
        this.board = new Board();

        // store all generated origin points to check for repitition
        Set<Point> originPoints = new HashSet<>();
        Set<Point> possibleEndPoints = new HashSet<>();
        Point originPoint = new Point();

        for (int shipLength : shipLengths) {

            // if duplicate, reroll origin point
            // if no possible endpoints, reroll origin point
            do {
                originPoint = new Point(rand.nextInt(Board.BOARD_SIZE), rand.nextInt(Board.BOARD_SIZE));
                Ship tempShip = new Ship(shipLength, originPoint);
                possibleEndPoints = board.checkPossible(tempShip);

            } while (originPoints.contains(originPoint) || possibleEndPoints.isEmpty());

            //add to set for duplicate checking
            originPoints.add(originPoint);

            Point endPoint = returnRandomPoint(possibleEndPoints);

            Ship ship = new Ship(shipLength, originPoint, endPoint);
            ships.add(ship);

            this.board.placeShip(ship);
        }
    }

     /**
     * Helper method for the initBoard method.
     * It returns a random point from the given set of points.
     * @param points a set of Point objects to choose from.
     * @return a randomly chosen Point object from the given set of points.
     */
    private Point returnRandomPoint(Set<Point> points) {
        int i = 0;
        int randIndex = rand.nextInt(points.size());
        for (Point point : points) {
            if (i == randIndex) {
                return point;
            }
            i++;
        }
        return new Point();
    }

     /**
     * Method to check if the AIEnemy player has lost the game.
     * @return a boolean value indicating if all the ships of the AIEnemy player have been sunk or not.
     */
    @Override
    public boolean checkLose() {
        this.shipsSunk = 0;
        for (Ship ship : this.ships) {
            this.shipsSunk += this.board.isSunk(ship) ? 1 : 0;
        }
        return (this.shipsSunk == this.ships.size());
    }
}
