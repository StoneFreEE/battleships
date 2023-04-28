package battleships;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AIEnemy extends Player {
        
    Random rand = new Random();

    public AIEnemy() {
        super();
    }

    // Ship placement randomiser
    @Override
    public void initBoard( int[] shipLengths) {
        ships = new ArrayList<>();
        this.board = new Board();

        // store all generated origin points to check for repitition
        Set<Point> originPoints = new HashSet<Point>();
        Set<Point> possibleEndPoints = new HashSet<Point>();
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

    // helper method for initBoard method
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

    @Override
    public boolean checkLose() {
        this.shipsSunk = 0;
        for (Ship ship : this.ships) {
            this.shipsSunk += this.board.isSunk(ship) ? 1 : 0;
        }
        return (this.shipsSunk == this.ships.size());
    }
}
