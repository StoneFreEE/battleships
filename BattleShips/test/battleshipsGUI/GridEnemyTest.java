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
public class GridEnemyTest {
    
    public GridEnemyTest() {
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
     * Test of setGamePanel method, of class GridEnemy.
     */
    @Test
    public void testSetGamePanel() {
        System.out.println("setGamePanel");
        FrameGame panel = null;
        GridEnemy instance = null;
        instance.setGamePanel(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnemy method, of class GridEnemy.
     */
    @Test
    public void testGetEnemy() {
        System.out.println("getEnemy");
        GridEnemy instance = null;
        AIEnemy expResult = null;
        AIEnemy result = instance.getEnemy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateGrid method, of class GridEnemy.
     */
    @Test
    public void testUpdateGrid() {
        System.out.println("updateGrid");
        Board board = null;
        GridEnemy instance = null;
        instance.updateGrid(board);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComponentAt method, of class GridEnemy.
     */
    @Test
    public void testGetComponentAt() {
        System.out.println("getComponentAt");
        int x = 0;
        int y = 0;
        GridEnemy instance = null;
        JPanel expResult = null;
        JPanel result = instance.getComponentAt(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBoard method, of class GridEnemy.
     */
    @Test
    public void testGetBoard() {
        System.out.println("getBoard");
        GridEnemy instance = null;
        Board expResult = null;
        Board result = instance.getBoard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startShootingPhase method, of class GridEnemy.
     */
    @Test
    public void testStartShootingPhase() {
        System.out.println("startShootingPhase");
        GridEnemy instance = null;
        instance.startShootingPhase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
