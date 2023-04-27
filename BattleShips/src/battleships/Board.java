/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.util.HashSet;

/**
 *
 * @author 64272
 *
 **/
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
        // Check if horizontla using origin and end point length
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
                if (cells[ship.origin.getY()][ship.origin.getX() - i] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point(ship.origin.getX() - (ship.length - 1), ship.origin.getY()));
            }
            place = true;
        }
        if (!(ship.origin.getX() + (ship.length - 1) > this.length - 1)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[ship.origin.getY()][ship.origin.getX() + i] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point(ship.origin.getX() + (ship.length - 1), ship.origin.getY()));
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
                possiblePoints.add(new Point(ship.origin.getX(), ship.origin.getY() - (ship.length - 1)));
            }
            place = true;
        }
        if (!(ship.origin.getY() + (ship.length - 1) > this.length - 1)) {
            for (int i = 0; i < ship.length; i++) {
                if (cells[ship.origin.getY() + i][ship.origin.getX()] == States.SHIP.ordinal()) {
                    place = false;
                }
            }
            if (place == true) {
                possiblePoints.add(new Point(ship.origin.getX(), ship.origin.getY() + (ship.length - 1)));
            }
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
                if (element == States.MISS.ordinal()) {
                    System.out.print(" # ");
                } else if (element == States.HIT.ordinal()) {
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
        return this.cells[point.getY()][point.getX()] != States.SHIP.ordinal();
    }
    // returns true if hit
    public boolean isHit(Point point) {
        return this.cells[point.getY()][point.getX()] == States.HIT.ordinal();
    }

    public boolean isMiss(Point point) {
        return this.cells[point.getY()][point.getX()] == States.MISS.ordinal();

    }
    // TODO return true if ship is sunk and false if ship is not sunk
    public boolean isSunk(Ship ship) {
        int sunkCellCount = 0;

        // if origin point x is same as end point x, ship is vertical 
        // if origin point y is higher than endpoint y, ship is oriented top to bottom
        if (ship.origin.getX() == ship.endPoint.getY()) {
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

    // set cell to dead cell
    public void setDead(Point point) {
        if (this.cells[point.getY()][point.getX()] == States.SHIP.ordinal()) {
            this.cells[point.getY()][point.getX()] = States.HIT.ordinal();
        } else {
            this.cells[point.getY()][point.getX()] = States.MISS.ordinal();

        }
    }
}
