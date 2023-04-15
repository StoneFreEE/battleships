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

public class Ship {
    int length;
    Point origin;
    Point endPoint;
    
    public Ship(int length, Point origin){
        this.length = length;
        this.origin = origin;
        this.endPoint = new Point();
    }
    
    public Ship(int length, Point origin, Point end){
        this.length = length;
        this.origin = origin;
        this.endPoint = end;
    }
    
    

    
}
