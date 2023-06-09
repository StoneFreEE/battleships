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
public class BoardTest {
    
    public BoardTest() {
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
     * Test of placeShip method, of class Board.
     */
    @Test
    public void testPlaceShip() {
        System.out.println("placeShip");
        Ship ship = null;
        Board instance = new Board();
        instance.placeShip(ship);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPossible method, of class Board.
     */
    @Test
    public void testCheckPossible() {
        System.out.println("checkPossible");
        Ship ship = null;
        Board instance = new Board();
        HashSet<Coordinate> expResult = null;
        HashSet<Coordinate> result = instance.checkPossible(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parsePoint method, of class Board.
     */
    @Test
    public void testParsePoint() {
        System.out.println("parsePoint");
        String text = "";
        Board instance = new Board();
        Coordinate expResult = null;
        Coordinate result = instance.parsePoint(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printBoard method, of class Board.
     */
    @Test
    public void testPrintBoard() {
        System.out.println("printBoard");
        Board instance = new Board();
        instance.printBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFree method, of class Board.
     */
    @Test
    public void testIsFree() {
        System.out.println("isFree");
        Coordinate point = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isFree(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSpace method, of class Board.
     */
    @Test
    public void testIsSpace() {
        System.out.println("isSpace");
        Ship ship = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isSpace(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHit method, of class Board.
     */
    @Test
    public void testIsHit() {
        System.out.println("isHit");
        Coordinate point = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isHit(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMiss method, of class Board.
     */
    @Test
    public void testIsMiss() {
        System.out.println("isMiss");
        Coordinate point = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isMiss(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSunk method, of class Board.
     */
    @Test
    public void testIsSunk() {
        System.out.println("isSunk");
        Ship ship = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.isSunk(ship);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class Board.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        Coordinate point = null;
        boolean expResult = false;
        boolean result = Board.isValid(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireAt method, of class Board.
     */
    @Test
    public void testFireAt() {
        System.out.println("fireAt");
        Coordinate point = null;
        Board instance = new Board();
        boolean expResult = false;
        boolean result = instance.fireAt(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
