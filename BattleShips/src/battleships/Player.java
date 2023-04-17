/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author 64272
 */
public class Player {

    Board board;
    int shipsSunk;

    public void initBoard(int boardLength, int[] shipLengths) {
        Scanner scanner = new Scanner(System.in);

        this.board = new Board(boardLength);

        int nShips = shipLengths.length;

        for (int i = 0; i < nShips; i++) {
            board.printBoard();
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
                    System.out.println("- " + letter + "" + ((int) point.getX() + 1));
                }
                String endResponse = scanner.nextLine();
                userEnd = board.parsePoint(endResponse);
                if (possiblePoints.contains(userEnd) && userEnd != null) {
                    Ship newShip = new Ship(shipLengths[i], userOrigin, userEnd);
                    board.placeShip(newShip);
                } else {
                    System.out.println("Invalid end point");
                }
            } while (!possiblePoints.contains(userEnd));
        }
    }

    public void testingInitBoard(int boardLength, int[] shipLengths) {
        this.board = new Board(boardLength);
        for (int i = 0; i < shipLengths.length; i++){
            Ship ship = new Ship(shipLengths[i], new Point(0, i), new Point(shipLengths[i], i));
            this.board.placeShip(ship);
        }
    }

    // TODO create method for firing cannon
    // Ask player which coordinate they want to fire cannon at
    // use olviers prompt parsing thing for this
    // 
}
