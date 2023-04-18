/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.awt.Point;
import java.util.HashSet;

/**
 *
 * @author 64272
 */
// idk where to put this but might need later
// 3 states, none, ship, deadship
enum States {
    NONE,
    SHIP,
    DEADSHIP,
    DEADNONE
}
// alter cells on cells
// display cells
// basically everything game cells related

public class Board {

    // 2d array to store cells
    public int[][] cells;
    private int length;

    public Board(int length) {
        // limit length value to between letters in alphabet
        if (length <= 26) {
            this.cells = new int[length][length];
            this.length = length;
        } else {
            this.cells = new int[26][length];
            this.length = 26;
        }
    }

    // Updates cells based on ship object passed in
    public void placeShip(Ship ship) {
        // change from display coordinates to cell coordinates
        // Check if vertical using the origin and end point y
        if ((int) ship.origin.getY() != (int) ship.endPoint.getY()) {
            // Check if going up or down
            boolean orientation = (int) ship.origin.getY() < (int) ship.endPoint.getY();
            if (orientation) {
                for (int j = (int) ship.origin.getY(); j < (int) ship.endPoint.getY() + 1; j++) {
                    cells[j][(int) ship.origin.getX()] = States.SHIP.ordinal();
                }
            } else {
                for (int j = (int) ship.endPoint.getY(); j < (int) ship.origin.getY() + 1; j++) {
                    cells[j][(int) ship.origin.getX()] = States.SHIP.ordinal();
                }
            }
        }
        // Check if horizontla using origin and end point length
        if ((int) ship.origin.getX() != (int) ship.endPoint.getX()) {
            // Check if going left or right
            boolean orientation = (int) ship.origin.getX() < (int) ship.endPoint.getX();
            if (orientation) {
                for (int j = (int) ship.origin.getX(); j < (int) ship.endPoint.getX() + 1; j++) {
                    cells[(int) ship.origin.getY()][j] = States.SHIP.ordinal();
                }
            } else {
                for (int j = (int) ship.endPoint.getX(); j < (int) ship.origin.getX() + 1; j++) {
                    cells[(int) ship.origin.getY()][j] = States.SHIP.ordinal();
                }
            }
        }
    }

    public int getLength() {
        return this.length;
    }

    // FINDS ALL POSSIBLE END POINTS
    public HashSet<Point> checkPossible(Ship ship) {
        HashSet<Point> possiblePoints = new HashSet<>();
        // If statements checks possible points within boundaries
        // For loop checks for already existing ships on board
        boolean place = true;
        if (!(ship.origin.getX() - (ship.length - 1) < 0)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[(int) ship.origin.getY()][(int) ship.origin.getX() - i] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point((int) ship.origin.getX() - (ship.length - 1), (int) ship.origin.getY()));
            }
            place = true;
        }
        if (!(ship.origin.getX() + (ship.length - 1) > this.length - 1)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[(int) ship.origin.getY()][(int) ship.origin.getX() + i] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point((int) ship.origin.getX() + (ship.length - 1), (int) ship.origin.getY()));
            }
            place = true;
        }
        if (!(ship.origin.getY() - (ship.length - 1) < 0)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[(int) ship.origin.getY() - i][(int) ship.origin.getX()] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point((int) ship.origin.getX(), (int) ship.origin.getY() - (ship.length - 1)));
            }
            place = true;
        }
        if (!(ship.origin.getY() + (ship.length - 1) > this.length - 1)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[(int) ship.origin.getY() + i][(int) ship.origin.getX()] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point((int) ship.origin.getX(), (int) ship.origin.getY() + (ship.length - 1)));
            }
            place = true;
        }

        return possiblePoints;
    }

    public Point parsePoint(String text) {
        // Check valid lengths
        if (text.length() > 3 || text.length() < 2) {
            return null;
        }
        // Convert a10 to A etc
        char ch = Character.toUpperCase(text.charAt(0));
        try {
            // Get last digits of text etc. a10 -> 10
            int digit = Integer.parseInt(text.substring(1));
            // Check within boundaries
            if (!(Character.isAlphabetic(ch)) || digit < 0 || digit > length || ch > (length - 1 + 'A')) {
                return null;
            }
            Point newPoint = new Point(digit - 1, ch - 'A');
            return newPoint;
        } catch (NumberFormatException e) {
            return null;
        }

    }

    public void printBoard() {

        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= this.getLength(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("\n");

        // print board
        char letter = 'A';
        for (int[] row : this.cells) {
            // print letter coordinates
            System.out.print(letter++ + "   ");

            for (int element : row) {
                if (element == States.DEADNONE.ordinal()) {
                    System.out.print(" # ");
                } else if (element == States.DEADSHIP.ordinal()) {
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

    // prints enemy board to player
    public void printEnemyBoard() {
        // print top of board
        System.out.print("    ");
        for (int i = 1; i <= this.getLength(); i++) {
            System.out.print(String.format("%2d", i) + " ");
        }
        System.out.println("\n");

        // print board
        char letter = 'A';
        for (int[] row : this.cells) {
            // print letter coordinates
            System.out.print(letter++ + "   ");
            for (int element : row) {
                if (element == States.DEADNONE.ordinal()) {
                    System.out.print(" # ");
                } else if (element == States.DEADSHIP.ordinal()) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" . ");
                }

            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    // returns true if free (not ship)
    public boolean isFree(Point point) {
        return cells[point.y][point.x] != States.SHIP.ordinal();
    }
    // returns true if deadship
    public boolean isHit(Point point) {
        return cells[point.y][point.x] == States.DEADSHIP.ordinal();
    }

    public boolean isDeadNone(Point point) {
        return cells[point.y][point.x] == States.DEADNONE.ordinal();

    }
    // TODO return true if ship is sunk and false if ship is not sunk
    public boolean isSunk(Ship ship) {
        int sunkCellCount = 0;

        // if origin point x is same as end point x, ship is vertical 
        // if origin point y is higher than endpoint y, ship is oriented top to bottom
        if (ship.origin.x == ship.endPoint.x) {
            for (int i = 0; i < ship.length; i++) {
                // check if all cells of ship are sunk cells
                if (ship.origin.y > ship.endPoint.y) {
                    if (this.cells[ship.origin.y - i][ship.origin.x] == States.DEADSHIP.ordinal()) {
                        sunkCellCount++;
                    }
                } else {
                    if (this.cells[ship.origin.y + i][ship.origin.x] == States.DEADSHIP.ordinal()) {
                        sunkCellCount++;
                    }
                }

            }
        } else {
            // if ship is horizontal
            for (int i = 0; i < ship.length; i++) {
                // check if all cells of ship are sunk cells
                // if origin x is greater than endpoint x, ship is oriented from right to left
                if (ship.origin.x > ship.endPoint.x) {
                    if (this.cells[ship.origin.y][ship.origin.x - i] == States.DEADSHIP.ordinal()) {
                        sunkCellCount++;
                    }
                } else {
                    if (this.cells[ship.origin.y][ship.origin.x + i] == States.DEADSHIP.ordinal()) {
                        sunkCellCount++;
                    }
                }

            }
        }
        return (sunkCellCount == ship.length);
    }

    // set cell to dead cell
    public void setDead(Point point) {
        if (this.cells[point.y][point.x] == States.SHIP.ordinal()) {
            this.cells[point.y][point.x] = States.DEADSHIP.ordinal();
        } else {
            this.cells[point.y][point.x] = States.DEADNONE.ordinal();

        }
    }
}
