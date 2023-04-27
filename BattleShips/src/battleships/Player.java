/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

import java.util.ArrayList;

/**
 *
 * @author oliver
 */
public abstract class Player {
    Board board;
    int shipsSunk;
    ArrayList<Ship> ships;
    
    public Player() {
        this.shipsSunk = 0;
    }
    
    public abstract void initBoard(int boardLength, int[] shipLengths);
    
    // loses if all battleships are sunk
    // updates shipsSunk
    public abstract boolean checkLose();
    
    public String translatePoint(Point point) {
        String out = "";
        out += (char)('A' + point.getY());
        out += point.getX() + 1;

        return out;
    }
}
