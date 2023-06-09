/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package battleshipsGUI;

import javax.swing.JPanel;
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
public class GridPlayerTest {
    
    public GridPlayerTest() {
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
     * Test of setEnemyTurn method, of class GridPlayer.
     */
    @Test
    public void testSetEnemyTurn() {
        System.out.println("setEnemyTurn");
        boolean bool = false;
        GridPlayer instance = null;
        instance.setEnemyTurn(bool);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGamePanel method, of class GridPlayer.
     */
    @Test
    public void testSetGamePanel() {
        System.out.println("setGamePanel");
        FrameGame panel = null;
        GridPlayer instance = null;
        instance.setGamePanel(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class GridPlayer.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        GridPlayer instance = null;
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShipLength method, of class GridPlayer.
     */
    @Test
    public void testSetShipLength() {
        System.out.println("setShipLength");
        int shipLength = 0;
        GridPlayer instance = null;
        instance.setShipLength(shipLength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayPossiblePoints method, of class GridPlayer.
     */
    @Test
    public void testDisplayPossiblePoints() {
        System.out.println("displayPossiblePoints");
        Coordinate[] points = null;
        GridPlayer instance = null;
        instance.displayPossiblePoints(points);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGrid method, of class GridPlayer.
     */
    @Test
    public void testUpdateGrid() {
        System.out.println("updateGrid");
        Board board = null;
        GridPlayer instance = null;
        instance.updateGrid(board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComponentAt method, of class GridPlayer.
     */
    @Test
    public void testGetComponentAt() {
        System.out.println("getComponentAt");
        int x = 0;
        int y = 0;
        GridPlayer instance = null;
        JPanel expResult = null;
        JPanel result = instance.getComponentAt(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class GridPlayer.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        GridPlayer instance = null;
        Board expResult = null;
        Board result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEnemyTargetLabel method, of class GridPlayer.
     */
    @Test
    public void testUpdateEnemyTargetLabel() {
        System.out.println("updateEnemyTargetLabel");
        String cell = "";
        GridPlayer instance = null;
        instance.updateEnemyTargetLabel(cell);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startShootingPhase method, of class GridPlayer.
     */
    @Test
    public void testStartShootingPhase() {
        System.out.println("startShootingPhase");
        GridPlayer instance = null;
        instance.startShootingPhase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEnemyResultLabel method, of class GridPlayer.
     */
    @Test
    public void testUpdateEnemyResultLabel() {
        System.out.println("updateEnemyResultLabel");
        String result_2 = "";
        GridPlayer instance = null;
        instance.updateEnemyResultLabel(result_2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
