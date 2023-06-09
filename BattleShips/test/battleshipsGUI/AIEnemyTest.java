/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package battleshipsGUI;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oliver
 */
public class AIEnemyTest {
    
    public AIEnemyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initBoard method, of class AIEnemy.
     */
    @Test
    public void testInitBoard() {
        AIEnemy instance = new AIEnemy();
        int[] shipLengths = {3, 4, 5}; // Example ship lengths

        instance.initBoard(shipLengths);

        // Verify that the ships list is not null
        assertNotNull(instance.getShipsList());

        // Verify that the number of ships created is equal to the number of ship lengths
        assertEquals(shipLengths.length, instance.getShipsList().size());

        // Verify that each ship in the ships list has valid coordinates
        for (Ship ship : instance.getShipsList()) {
            assertNotNull(ship.getOrigin());
            assertNotNull(ship.getEndPoint());
        }
    }

    /**
     * Test of checkLose method, of class AIEnemy.
     */
   @Test
    public void testCheckLose() {
        AIEnemy instance = new AIEnemy();
        
        // Create a ship and add it to the AIEnemy's ships list
        Ship ship = new Ship(3, new Coordinate(0, 0), new Coordinate(0, 2));
        instance.getShipsList().add(ship);

        // Verify that checkLose returns false before the ship is sunk
        assertFalse(instance.checkLose());

        // Sink the ship
        instance.getBoard().sinkShip(ship);

        // Verify that checkLose returns true after the ship is sunk
        assertTrue(instance.checkLose());
    }

    /**
     * Test of getShipsList method, of class AIEnemy.
     */
    /**
     * Test the getShipsList method of AIEnemy.
     */
    @Test
    public void testGetShipsList() {
        AIEnemy instance = new AIEnemy();
        ArrayList<Ship> shipsList = instance.getShipsList();

        // Verify that the returned ships list is not null
        assertNotNull(shipsList);

        // Verify that the returned ships list is initially empty
        assertTrue(shipsList.isEmpty());

        // Add a ship to the ships list
        Ship ship = new Ship(3, new Coordinate(0, 0), new Coordinate(0, 2));
        shipsList.add(ship);

        // Verify that the ships list is not empty after adding a ship
        assertFalse(shipsList.isEmpty());

        // Verify that adding 1 ship returns a size of 1
        assertEquals(1, instance.getShipsList().size());
    }
    
}
