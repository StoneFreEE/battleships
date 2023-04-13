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
// alter cells on board
// display board
// basically everything game board related

public class Board extends BoardDisplay {

    public Board(int x, int y) {
        super(x, y);
    }
    // create place ship method
    // check if ship has been sunk
    // update state of board after each turn

    // when placing ships place using origin of leftmost point (if ship horizontal)
    // or topmost point (if ship vertical)
}
