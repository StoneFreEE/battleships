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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of initBoard method, of class Player.
     */
    @Test
    public void testInitBoard() {
        System.out.println("initBoard");
        int[] shipLengths = null;
        Player instance = new PlayerImpl();
        instance.initBoard(shipLengths);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkLose method, of class Player.
     */
    @Test
    public void testCheckLose() {
        System.out.println("checkLose");
        Player instance = new PlayerImpl();
        boolean expResult = false;
        boolean result = instance.checkLose();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShipsList method, of class Player.
     */
    @Test
    public void testGetShipsList() {
        System.out.println("getShipsList");
        Player instance = new PlayerImpl();
        ArrayList<Ship> expResult = null;
        ArrayList<Ship> result = instance.getShipsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlayerImpl extends Player {

        public void initBoard(int[] shipLengths) {
        }

        public boolean checkLose() {
            return false;
        }

        public ArrayList<Ship> getShipsList() {
            return null;
        }
    }
    
}
