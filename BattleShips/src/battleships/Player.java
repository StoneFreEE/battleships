/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author 64272
 */
public class Player {

    Board playerBoard;
    int shipsSunk;
    ArrayList<Ship> ships;

    public Player() {
        this.shipsSunk = 0;
    }

    public void initBoard(int boardLength, int[] shipLengths) {
        ships = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        this.playerBoard = new Board(boardLength);

        int nShips = shipLengths.length;

        for (int i = 0; i < nShips; i++) {
            System.out.println("Placing ship of length " + shipLengths[i]);
            HashSet<Point> possiblePoints = new HashSet<>();
            Point userOrigin = new Point();

            do {
                System.out.println("Pick an origin (e.g C4): ");
                String originResponse = scanner.nextLine();
                userOrigin = playerBoard.parsePoint(originResponse);
                if (userOrigin != null) {
                    Ship ship = new Ship(shipLengths[i], userOrigin);
                    possiblePoints = playerBoard.checkPossible(ship);
                    if (possiblePoints.isEmpty()) {
                        System.out.println("No possible end points found");
                        System.out.println("Choose another origin");
                    }
                } else {
                    System.out.println("Invalid origin");
                }
                System.out.println("");
            } while (possiblePoints.isEmpty() || !playerBoard.isFree(userOrigin) || userOrigin == null);

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
                userEnd = playerBoard.parsePoint(endResponse);
                if (possiblePoints.contains(userEnd) && userEnd != null) {
                    Ship newShip = new Ship(shipLengths[i], userOrigin, userEnd);
                    playerBoard.placeShip(newShip);
                } else {
                    System.out.println("Invalid end point");
                }
            } while (!possiblePoints.contains(userEnd));
        }
    }

    //places all ships at x = 0 
    public void testingInitBoard(int boardLength, int[] shipLengths) {
        ships = new ArrayList<>();

        this.playerBoard = new Board(boardLength);

        for (int i = 0; i < shipLengths.length; i++) {
            Ship ship = new Ship(shipLengths[i], new Point(0, i), new Point(shipLengths[i] - 1, i));
            ships.add(ship);
            this.playerBoard.placeShip(ship);
        }
    }

    // wins if all battleships are sunk
    // updates shipsSunk
    public Boolean checkLose() {
        this.shipsSunk = 0;
        for (Ship ship : this.ships) {
            this.shipsSunk += this.playerBoard.isSunk(ship) ? 1 : 0;
        }
        return (this.shipsSunk == this.ships.size());
    }
}
