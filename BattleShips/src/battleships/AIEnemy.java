/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author 64272
 */
// 
public class AIEnemy {

    Board board;
    int shipsSunk;

    Random rand = new Random();

    // Ship placement randomiser
    public void initBoard(int boardLength, int[] shipLengths) {
        this.board = new Board(boardLength);
        
        // store all generated origin points to check for repitition
        Set<Point> originPoints = new HashSet<Point>();
        Set<Point> possibleEndPoints = new HashSet<Point>();
        Point originPoint = new Point();

        for (int shipLength : shipLengths) {

            // if duplicate, reroll origin point
            // if no possible endpoints, reroll origin point
            do {
                originPoint = new Point(rand.nextInt(board.getLength()), rand.nextInt(board.getLength()));
                Ship tempShip = new Ship(shipLength, originPoint);
                possibleEndPoints = board.checkPossible(tempShip);

            } while (originPoints.contains(originPoint) || possibleEndPoints.isEmpty());

            //add to set for duplicate checking
            originPoints.add(originPoint);

            Point endPoint = returnRandomPoint(possibleEndPoints);

            Ship ship = new Ship(shipLength, originPoint, endPoint);

            this.board.placeShip(ship);
        }
    }

    // returns a random point from a set of points
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

    //TODO Create fire cannon method
    
    //TODO Create SMART CANNON SHOOTING METHOD
}
