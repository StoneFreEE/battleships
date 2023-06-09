package battleshipsGUI;

import battleshipsGUI.Coordinate;
import battleshipsGUI.Ship;
import battleshipsGUI.User;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testSetName() {
        User instance = new User();
        instance.setName("John");
        assertEquals("John", instance.getName());
    }

    @Test
    public void testSetScore() {
        User instance = new User();
        instance.setScore(100);
        assertEquals(100, instance.getScore());
    }

    @Test
    public void testGetScore() {
        User instance = new User();
        assertEquals(500, instance.getScore());
    }

    @Test
    public void testGetName() {
        User instance = new User();
        assertEquals("", instance.getName());
    }

    @Test
    public void testToString() {
        User instance = new User("John", 100);
        assertEquals("John 100", instance.toString());
    }

    @Test
    public void testSetShips() {
        User instance = new User();
        ArrayList<Ship> ships = new ArrayList<>();
        Ship ship = new Ship(3, new Coordinate(1, 1), new Coordinate(1, 3));
        ships.add(ship);
        instance.setShips(ships);
        assertEquals(ships, instance.getShipsList());
    }

    @Test
    public void testContains() {
        User instance = new User();
        HashSet<Coordinate> points = new HashSet<>();
        Coordinate end = new Coordinate(1, 2);
        points.add(new Coordinate(1, 1));
        points.add(new Coordinate(1, 2));
        points.add(new Coordinate(1, 3));
        assertTrue(instance.contains(points, end));
    }

    @Test
    public void testCheckLose() {
        User instance = new User();
        assertTrue(instance.checkLose());

        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(new Ship(2, new Coordinate(1, 1), new Coordinate(1, 2)));
        instance.setShips(ships);
        assertFalse(instance.checkLose());

        instance.getBoard().fireAt(new Coordinate(1, 1));
        instance.getBoard().fireAt(new Coordinate(1, 2));
        assertFalse(instance.checkLose());
    }

    @Test
    public void testCompareTo() {
        User user1 = new User("John", 100);
        User user2 = new User("Jane", 200);
        assertTrue(user1.compareTo(user2) < 0);
        assertTrue(user2.compareTo(user1) > 0);

        User user3 = new User("John", 200);
        // should be equal to difference in scores 
        // (john100 has less of a score than john200)
        assertEquals(-100, user1.compareTo(user3));
    }

    @Test
    public void testEquals() {
        User user1 = new User("John", 100);
        User user2 = new User("John", 200);
        User user3 = new User("Jane", 100);

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));
    }

    @Test
    public void testHashCode() {
        User user1 = new User("John", 100);
        User user2 = new User("John", 100);

        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
