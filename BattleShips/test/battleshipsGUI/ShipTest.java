package battleshipsGUI;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testGetOrigin() {
        Coordinate origin = new Coordinate(2, 3);
        Ship ship = new Ship(4, origin);
        assertEquals(origin, ship.getOrigin());
    }

    @Test
    public void testGetLength() {
        int length = 4;
        Ship ship = new Ship(length, new Coordinate(2, 3));
        assertEquals(length, ship.getLength());
    }

    @Test
    public void testGetEndPoint() {
        Coordinate endPoint = new Coordinate(5, 3);
        Ship ship = new Ship(4, new Coordinate(2, 3), endPoint);
        assertEquals(endPoint, ship.getEndPoint());
    }
}
