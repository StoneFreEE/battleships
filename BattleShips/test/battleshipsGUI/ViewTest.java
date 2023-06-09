/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package battleshipsGUI;

import java.awt.event.KeyEvent;
import java.util.Observable;
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
public class ViewTest {
    
    public ViewTest() {
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
     * Test of initiateStartScreen method, of class View.
     */
    @Test
    public void testInitiateStartScreen() {
        System.out.println("initiateStartScreen");
        View instance = null;
        instance.initiateStartScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initStartScreen method, of class View.
     */
    @Test
    public void testInitStartScreen() {
        System.out.println("initStartScreen");
        View instance = null;
        instance.initStartScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setController method, of class View.
     */
    @Test
    public void testSetController() {
        System.out.println("setController");
        Controller controller = null;
        View instance = null;
        instance.setController(controller);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class View.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        View instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promptSave method, of class View.
     */
    @Test
    public void testPromptSave() {
        System.out.println("promptSave");
        GridPlayer grid = null;
        View instance = null;
        instance.promptSave(grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initiateBoard method, of class View.
     */
    @Test
    public void testInitiateBoard() {
        System.out.println("initiateBoard");
        int[] shipLengths = null;
        View instance = null;
        instance.initiateBoard(shipLengths);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playGame method, of class View.
     */
    @Test
    public void testPlayGame() {
        System.out.println("playGame");
        View instance = null;
        instance.playGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gameOver method, of class View.
     */
    @Test
    public void testGameOver() {
        System.out.println("gameOver");
        String winner = "";
        int score = 0;
        View instance = null;
        instance.gameOver(winner, score);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLeaderboard method, of class View.
     */
    @Test
    public void testDisplayLeaderboard() {
        System.out.println("displayLeaderboard");
        Object[][] users = null;
        View instance = null;
        instance.displayLeaderboard(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class View.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        View instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayErrorBoard method, of class View.
     */
    @Test
    public void testDisplayErrorBoard() {
        System.out.println("displayErrorBoard");
        View instance = null;
        instance.displayErrorBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayOverwritePrompt method, of class View.
     */
    @Test
    public void testDisplayOverwritePrompt() {
        System.out.println("displayOverwritePrompt");
        String boardName = "";
        View instance = null;
        instance.displayOverwritePrompt(boardName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGrid method, of class View.
     */
    @Test
    public void testGetGrid() {
        System.out.println("getGrid");
        View instance = null;
        GridPlayer expResult = null;
        GridPlayer result = instance.getGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayErrorPlacementMessage method, of class View.
     */
    @Test
    public void testDisplayErrorPlacementMessage() {
        System.out.println("displayErrorPlacementMessage");
        View instance = null;
        instance.displayErrorPlacementMessage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class View.
     */
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent e = null;
        View instance = null;
        instance.keyPressed(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyTyped method, of class View.
     */
    @Test
    public void testKeyTyped() {
        System.out.println("keyTyped");
        KeyEvent e = null;
        View instance = null;
        instance.keyTyped(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyReleased method, of class View.
     */
    @Test
    public void testKeyReleased() {
        System.out.println("keyReleased");
        KeyEvent e = null;
        View instance = null;
        instance.keyReleased(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
