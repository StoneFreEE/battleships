/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package battleshipsGUI;

import java.util.HashSet;
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
public class ModelTest {
    
    public ModelTest() {
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
     * Test of getUsers method, of class Model.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Model instance = new Model();
        Object[][] expResult = null;
        Object[][] result = instance.getUsers();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dbsetup method, of class Model.
     */
    @Test
    public void testDbsetup() {
        System.out.println("dbsetup");
        Model instance = new Model();
        instance.dbsetup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUnique method, of class Model.
     */
    @Test
    public void testCheckUnique() {
        System.out.println("checkUnique");
        String username = "";
        Model instance = new Model();
        instance.checkUnique(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertBoard method, of class Model.
     */
    @Test
    public void testInsertBoard() {
        System.out.println("insertBoard");
        String boardName = "";
        User user = null;
        Model instance = new Model();
        instance.insertBoard(boardName, user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBoard method, of class Model.
     */
    @Test
    public void testUpdateBoard() {
        System.out.println("updateBoard");
        String boardName = "";
        User user = null;
        Model instance = new Model();
        instance.updateBoard(boardName, user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePlayerScore method, of class Model.
     */
    @Test
    public void testUpdatePlayerScore() {
        System.out.println("updatePlayerScore");
        Model instance = new Model();
        instance.updatePlayerScore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class Model.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        Model instance = new Model();
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScore method, of class Model.
     */
    @Test
    public void testSetScore() {
        System.out.println("setScore");
        int score = 0;
        Model instance = new Model();
        instance.setScore(score);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipLengths method, of class Model.
     */
    @Test
    public void testGetShipLengths() {
        System.out.println("getShipLengths");
        Model instance = new Model();
        int[] expResult = null;
        int[] result = instance.getShipLengths();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class Model.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        Model instance = new Model();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateScore method, of class Model.
     */
    @Test
    public void testUpdateScore() {
        System.out.println("updateScore");
        Model instance = new Model();
        instance.updateScore();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBoardName method, of class Model.
     */
    @Test
    public void testSetBoardName() {
        System.out.println("setBoardName");
        String boardName = "";
        Model instance = new Model();
        instance.setBoardName(boardName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setController method, of class Model.
     */
    @Test
    public void testSetController() {
        System.out.println("setController");
        Controller controller = null;
        Model instance = new Model();
        instance.setController(controller);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initPlayerGrid method, of class Model.
     */
    @Test
    public void testInitPlayerGrid() {
        System.out.println("initPlayerGrid");
        GridPlayer grid = null;
        Model instance = new Model();
        instance.initPlayerGrid(grid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initEnemyGrid method, of class Model.
     */
    @Test
    public void testInitEnemyGrid() {
        System.out.println("initEnemyGrid");
        Model instance = new Model();
        instance.initEnemyGrid();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerGrid method, of class Model.
     */
    @Test
    public void testGetPlayerGrid() {
        System.out.println("getPlayerGrid");
        Model instance = new Model();
        GridPlayer expResult = null;
        GridPlayer result = instance.getPlayerGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enemyFireCannon method, of class Model.
     */
    @Test
    public void testEnemyFireCannon() {
        System.out.println("enemyFireCannon");
        Model instance = new Model();
        instance.enemyFireCannon();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of linkPaneltoGrid method, of class Model.
     */
    @Test
    public void testLinkPaneltoGrid() {
        System.out.println("linkPaneltoGrid");
        FrameGame panel = null;
        Model instance = new Model();
        instance.linkPaneltoGrid(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnemyGrid method, of class Model.
     */
    @Test
    public void testGetEnemyGrid() {
        System.out.println("getEnemyGrid");
        Model instance = new Model();
        GridEnemy expResult = null;
        GridEnemy result = instance.getEnemyGrid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Model.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Model instance = new Model();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initEnemy method, of class Model.
     */
    @Test
    public void testInitEnemy() {
        System.out.println("initEnemy");
        Model instance = new Model();
        instance.initEnemy();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadBoard method, of class Model.
     */
    @Test
    public void testLoadBoard() {
        System.out.println("loadBoard");
        Model instance = new Model();
        instance.loadBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrigin method, of class Model.
     */
    @Test
    public void testSetOrigin() {
        System.out.println("setOrigin");
        Coordinate coordinate = null;
        Model instance = new Model();
        instance.setOrigin(coordinate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnd method, of class Model.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        Coordinate coordinate = null;
        Model instance = new Model();
        instance.setEnd(coordinate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertToPrimitive method, of class Model.
     */
    @Test
    public void testConvertToPrimitive() {
        System.out.println("convertToPrimitive");
        HashSet<Coordinate> possiblePoints = null;
        Model instance = new Model();
        Coordinate[] expResult = null;
        Coordinate[] result = instance.convertToPrimitive(possiblePoints);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkValid method, of class Model.
     */
    @Test
    public void testCheckValid() {
        System.out.println("checkValid");
        Coordinate coordinate = null;
        int shipLength = 0;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.checkValid(coordinate, shipLength);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
