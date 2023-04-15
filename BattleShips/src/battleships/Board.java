/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.awt.Point;
import java.util.HashMap;
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
    DEADSHIP
}
// alter cells on cells
// display cells
// basically everything game cells related

public class Board {

    // 2d array to store cells
    public int[][] cells;
    private int x;
    private int y;

    public Board(int x, int y) {
        this.x = x;
        // limit y value to between letters in alphabet
        if (y <= 26) {
            this.cells = new int[y][x];
            this.y = y;
        } else {
            this.cells = new int[26][x];
            this.y = 26;
        }
    }

    // TODO create place ship method
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
        // Check if horizontla using origin and end point x
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
    
    public boolean isFree(Point point) {
        if (cells[(int)point.getY()][(int)point.getX()] == States.SHIP.ordinal()) {
            return false;
        }
        return true;
    }

    public int getX() {
        return this.x;
    }

    public HashSet<Point> checkPossible(Point origin, Ship ship) {
        HashSet<Point> possiblePoints = new HashSet<Point>();
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
        if (!(ship.origin.getX() + (ship.length - 1) > this.x - 1)) {
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
        if (!(ship.origin.getY() + (ship.length - 1) > this.y - 1)) {
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
            if (!(Character.isAlphabetic(ch)) || digit < 0 || digit > x || ch > (y - 1 + 'A')) {
                return null;
            }
            Point newPoint = new Point(digit - 1, ch - 'A');
            return newPoint;
        } catch (NumberFormatException e) {
            return null;
        }
        
    }
}

// TODO check if ship has been sunk
// TODO update state of cells after each turn
// placing ship idea !
// when placing ships place using origin of leftmost point (if ship horizontal)
// or topmost point (if ship vertical)
