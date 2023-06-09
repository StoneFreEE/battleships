package battleshipsGUI;

import battleshipsGUI.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateTest {

    @Test
    public void testGetX() {
        Coordinate instance = new Coordinate();
        int expected = 0;
        int result = instance.getX();
        assertEquals(expected, result);
    }

    @Test
    public void testSetX() {
        Coordinate instance = new Coordinate();
        int expected = 5;
        instance.setX(expected);
        int result = instance.getX();
        assertEquals(expected, result);
    }

    @Test
    public void testGetY() {
        Coordinate instance = new Coordinate();
        int expected = 0;
        int result = instance.getY();
        assertEquals(expected, result);
    }

    @Test
    public void testSetY() {
        Coordinate instance = new Coordinate();
        int expected = 3;
        instance.setY(expected);
        int result = instance.getY();
        assertEquals(expected, result);
    }

    @Test
    public void testEquals() {
        Coordinate instance1 = new Coordinate(2, 3);
        Coordinate instance2 = new Coordinate(2, 3);
        Coordinate instance3 = new Coordinate(4, 5);

        assertTrue(instance1.equals(instance2));
        assertFalse(instance1.equals(instance3));
    }

    @Test
    public void testHashCode() {
        Coordinate instance1 = new Coordinate(2, 3);
        Coordinate instance2 = new Coordinate(2, 3);
        Coordinate instance3 = new Coordinate(4, 5);

        assertEquals(instance1.hashCode(), instance2.hashCode());
        assertNotEquals(instance1.hashCode(), instance3.hashCode());
    }

    @Test
    public void testToString() {
        Coordinate instance = new Coordinate(2, 3);
        String expected = "23";
        String result = instance.toString();
        assertEquals(expected, result);
    }

    @Test
    public void testTranslatePoint() {
        Coordinate point = new Coordinate(2, 1);
        String expected = "B3";
        String result = Coordinate.translatePoint(point);
        assertEquals(expected, result);
    }
}
