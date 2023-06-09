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
public class FrameGameTest {
    
    public FrameGameTest() {
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
     * Test of updateUserClickLabel method, of class FrameGame.
     */
    @Test
    public void testUpdateUserClickLabel() {
        System.out.println("updateUserClickLabel");
        String cell = "";
        FrameGame instance = null;
        instance.updateUserClickLabel(cell);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTurn method, of class FrameGame.
     */
    @Test
    public void testUpdateTurn() {
        System.out.println("updateTurn");
        FrameGame instance = null;
        instance.updateTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementScore method, of class FrameGame.
     */
    @Test
    public void testIncrementScore() {
        System.out.println("incrementScore");
        FrameGame instance = null;
        instance.incrementScore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateErrorLabel method, of class FrameGame.
     */
    @Test
    public void testUpdateErrorLabel() {
        System.out.println("updateErrorLabel");
        boolean isValid = false;
        FrameGame instance = null;
        instance.updateErrorLabel(isValid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEnemyTargetLabel method, of class FrameGame.
     */
    @Test
    public void testUpdateEnemyTargetLabel() {
        System.out.println("updateEnemyTargetLabel");
        String cell = "";
        FrameGame instance = null;
        instance.updateEnemyTargetLabel(cell);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePlayerResultLabel method, of class FrameGame.
     */
    @Test
    public void testUpdatePlayerResultLabel() {
        System.out.println("updatePlayerResultLabel");
        String result_2 = "";
        FrameGame instance = null;
        instance.updatePlayerResultLabel(result_2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateShipsRemaining method, of class FrameGame.
     */
    @Test
    public void testUpdateShipsRemaining() {
        System.out.println("updateShipsRemaining");
        FrameGame instance = null;
        instance.updateShipsRemaining();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEnemyResultLabel method, of class FrameGame.
     */
    @Test
    public void testUpdateEnemyResultLabel() {
        System.out.println("updateEnemyResultLabel");
        String result_2 = "";
        FrameGame instance = null;
        instance.updateEnemyResultLabel(result_2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkWin method, of class FrameGame.
     */
    @Test
    public void testCheckWin() {
        System.out.println("checkWin");
        FrameGame instance = null;
        String expResult = "";
        String result = instance.checkWin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkWinner method, of class FrameGame.
     */
    @Test
    public void testCheckWinner() {
        System.out.println("checkWinner");
        FrameGame instance = null;
        instance.checkWinner();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
