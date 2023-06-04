package battleshipsGUI;

import battleships.*;

/**
 * A class representing a point in a 2D space with integer x and y coordinates.
 */
public class Coordinate {
    public int x;
    public int y;
    
    /**
     * Constructs a Point object with given x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructs a Point object with default x and y coordinates of 0.
     */
    public Coordinate() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Gets the x coordinate of this Coordinate.
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate of this Coordinate to the given value.
     * @param x the new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y coordinate of this Coordinate.
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate of this Coordinate to the given value.
     * @param y the new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Checks whether this Coordinate is equal to the given object.
     * @param o the object to compare to
     * @return true if the object is a Coordinate with the same x and y coordinates,
 false otherwise
     */
    @Override
    public boolean equals(Object o) {
        Coordinate point = (Coordinate) o;
        return point.getX() == this.getX() && point.getY() == this.getY();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
        return hash;
    }
    
    /**
     * Returns a String representation of this Coordinate.
     * @return a string representation of this Coordinate in the format "xy" where 
 x and y are the coordinates
     */
    @Override
    public String toString() {
        return this.getX() + "" + this.getY();
    }
    
    /**
     * Method to translate a Coordinate object into a string representation of the 
 coordinates.
     * 
     * @param point The Coordinate object to be translated.
     * @return A string representing the coordinates of the Coordinate.
     */
    public static String translatePoint(Coordinate point) {
        String out = "";
        out += (char)('A' + point.getY());
        out += point.getX() + 1;

        return out;
    }
}
