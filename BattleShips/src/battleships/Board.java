/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

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
        int shipX = ship.x - 1;
        int shipY = ship.y - 1;

        // TODO heck if ship is within board bounds
        
        // check orientation of ship
        if (ship.horizontal) {
            for (int x = shipX; x < shipX + ship.length; x++) {
                cells[shipY][x] = States.SHIP.ordinal();
            }
        } else {
            for (int y = shipY; y < shipY + ship.length; y++) {
                cells[y][shipX] = States.SHIP.ordinal();
            }
        }

    }

    public int getX() {
        return this.x;
    }
}

// TODO check if ship has been sunk
// TODO update state of cells after each turn
// placing ship idea !
// when placing ships place using origin of leftmost point (if ship horizontal)
// or topmost point (if ship vertical)
