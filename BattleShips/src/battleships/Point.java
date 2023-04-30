package battleships;

/**
 * A class representing a point in a 2D space with integer x and y coordinates.
 */
public class Point {
    private int x;
    private int y;
    
    /**
     * Constructs a Point object with given x and y coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructs a Point object with default x and y coordinates of 0.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Gets the x coordinate of this Point.
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate of this Point to the given value.
     * @param x the new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y coordinate of this Point.
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate of this Point to the given value.
     * @param y the new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Checks whether this Point is equal to the given object.
     * @param o the object to compare to
     * @return true if the object is a Point with the same x and y coordinates,
     * false otherwise
     */
    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
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
     * Returns a String representation of this Point.
     * @return a string representation of this Point in the format "xy" where 
     * x and y are the coordinates
     */
    @Override
    public String toString() {
        return this.getX() + "" + this.getY();
    }
    
    /**
     * Method to translate a Point object into a string representation of the 
     * coordinates.
     * 
     * @param point The Point object to be translated.
     * @return A string representing the coordinates of the Point.
     */
    public static String translatePoint(Point point) {
        String out = "";
        out += (char)('A' + point.getY());
        out += point.getX() + 1;

        return out;
    }
}
