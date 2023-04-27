/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleships;

/**
 *
 * @author oliver
 */
// To emulate x, y coordinate
public class Point {
    public int x;
    public int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        return point.getX() == this.getX() && point.getY() == this.getY();
    }
    
    @Override
    public String toString() {
        return this.getX() + "" + this.getY();
    }
}
