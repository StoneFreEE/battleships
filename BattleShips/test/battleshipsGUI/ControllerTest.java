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
public class ControllerTest {
    
    public ControllerTest() {
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
     * Test of startGame method, of class Controller.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        Controller instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playGame method, of class Controller.
     */
    @Test
    public void testPlayGame() {
        System.out.println("playGame");
        Controller instance = null;
        instance.playGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playFromLoad method, of class Controller.
     */
    @Test
    public void testPlayFromLoad() {
        System.out.println("playFromLoad");
        Controller instance = null;
        instance.playFromLoad();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPlayerGameGrid method, of class Controller.
     */
    @Test
    public void testLoadPlayerGameGrid() {
        System.out.println("loadPlayerGameGrid");
        GridPlayer grid = null;
        Controller instance = null;
        instance.loadPlayerGameGrid(grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadBoard method, of class Controller.
     */
    @Test
    public void testLoadBoard() {
        System.out.println("loadBoard");
        String boardName = "";
        Controller instance = null;
        instance.loadBoard(boardName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayLeaderboard method, of class Controller.
     */
    @Test
    public void testDisplayLeaderboard() {
        System.out.println("displayLeaderboard");
        Controller instance = null;
        instance.displayLeaderboard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnemyGrid method, of class Controller.
     */
    @Test
    public void testSetEnemyGrid() {
        System.out.println("setEnemyGrid");
        Controller instance = null;
        instance.setEnemyGrid();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of linkFrametoGrid method, of class Controller.
     */
    @Test
    public void testLinkFrametoGrid() {
        System.out.println("linkFrametoGrid");
        FrameGame panel = null;
        Controller instance = null;
        instance.linkFrametoGrid(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTurn method, of class Controller.
     */
    @Test
    public void testUpdateTurn() {
        System.out.println("updateTurn");
        Controller instance = null;
        instance.updateTurn();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerGameGrid method, of class Controller.
     */
    @Test
    public void testSetPlayerGameGrid() {
        System.out.println("setPlayerGameGrid");
        Controller instance = null;
        instance.setPlayerGameGrid();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Controller.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Controller instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateScore method, of class Controller.
     */
    @Test
    public void testUpdateScore() {
        System.out.println("updateScore");
        Controller instance = null;
        instance.updateScore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePlayerScore method, of class Controller.
     */
    @Test
    public void testUpdatePlayerScore() {
        System.out.println("updatePlayerScore");
        Controller instance = null;
        instance.updatePlayerScore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initiateBoard method, of class Controller.
     */
    @Test
    public void testInitiateBoard() {
        System.out.println("initiateBoard");
        Controller instance = null;
        instance.initiateBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrigin method, of class Controller.
     */
    @Test
    public void testSetOrigin() {
        System.out.println("setOrigin");
        Coordinate coordinate = null;
        Controller instance = null;
        instance.setOrigin(coordinate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initiateStartScreen method, of class Controller.
     */
    @Test
    public void testInitiateStartScreen() {
        System.out.println("initiateStartScreen");
        Controller instance = null;
        instance.initiateStartScreen();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnd method, of class Controller.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        Coordinate coordinate = null;
        Controller instance = null;
        instance.setEnd(coordinate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gameOver method, of class Controller.
     */
    @Test
    public void testGameOver() {
        System.out.println("gameOver");
        String winner = "";
        int score = 0;
        Controller instance = null;
        instance.gameOver(winner, score);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkValid method, of class Controller.
     */
    @Test
    public void testCheckValid() {
        System.out.println("checkValid");
        Coordinate coordinate = null;
        int shipLength = 0;
        Controller instance = null;
        boolean expResult = false;
        boolean result = instance.checkValid(coordinate, shipLength);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScore method, of class Controller.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        int score = 0;
        Controller instance = null;
        instance.setScore(score);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveBoard method, of class Controller.
     */
    @Test
    public void testSaveBoard() {
        System.out.println("saveBoard");
        String boardName = "";
        Controller instance = null;
        instance.saveBoard(boardName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBoard method, of class Controller.
     */
    @Test
    public void testUpdateBoard() {
        System.out.println("updateBoard");
        String boardName = "";
        Controller instance = null;
        instance.updateBoard(boardName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of promptSave method, of class Controller.
     */
    @Test
    public void testPromptSave() {
        System.out.println("promptSave");
        GridPlayer grid = null;
        Controller instance = null;
        instance.promptSave(grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
