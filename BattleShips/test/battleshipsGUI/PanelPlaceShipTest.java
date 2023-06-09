/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package battleshipsGUI;

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
public class PanelPlaceShipTest {
    
    public PanelPlaceShipTest() {
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
     * Test of displayPossiblePoints method, of class PanelPlaceShip.
     */
    @Test
    public void testDisplayPossiblePoints() {
        System.out.println("displayPossiblePoints");
        Coordinate[] points = null;
        PanelPlaceShip instance = null;
        instance.displayPossiblePoints(points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGrid method, of class PanelPlaceShip.
     */
    @Test
    public void testUpdateGrid() {
        System.out.println("updateGrid");
        User user = null;
        PanelPlaceShip instance = null;
        instance.updateGrid(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGrid method, of class PanelPlaceShip.
     */
    @Test
    public void testGetGrid() {
        System.out.println("getGrid");
        PanelPlaceShip instance = null;
        GridPlayer expResult = null;
        GridPlayer result = instance.getGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayErrorPlacementMessage method, of class PanelPlaceShip.
     */
    @Test
    public void testDisplayErrorPlacementMessage() {
        System.out.println("displayErrorPlacementMessage");
        PanelPlaceShip instance = null;
        instance.displayErrorPlacementMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
