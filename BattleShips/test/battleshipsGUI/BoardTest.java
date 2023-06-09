package battleshipsGUI;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashSet;

public class BoardTest {

    @Test
    public void testPlaceShip() {
        Board board = new Board();
        Ship ship = new Ship(3, new Coordinate(0, 0), new Coordinate(0, 2));
        board.placeShip(ship);

        assertEquals(GridCellStates.SHIP.ordinal(), board.cells[0][0]);
        assertEquals(GridCellStates.SHIP.ordinal(), board.cells[1][0]);
        assertEquals(GridCellStates.SHIP.ordinal(), board.cells[2][0]);

        ship = new Ship(3, new Coordinate(5, 5), new Coordinate(7, 5));
        board.placeShip(ship);

        assertEquals(GridCellStates.SHIP.ordinal(), board.cells[5][5]);
        assertEquals(GridCellStates.SHIP.ordinal(), board.cells[5][6]);
        assertEquals(GridCellStates.SHIP.ordinal(), board.cells[5][7]);
    }

    @Test
    public void testCheckPossible() {
        Board board = new Board();

        Ship ship = new Ship(3, new Coordinate(5, 5), new Coordinate(7, 5));
        HashSet<Coordinate> expected = new HashSet<>();

        // test for possible end points
        expected = new HashSet<>();
        expected.add(new Coordinate(5, 3));
        expected.add(new Coordinate(5, 7));
        expected.add(new Coordinate(3, 5));
        expected.add(new Coordinate(7, 5));

        assertEquals(expected, board.checkPossible(ship));
    }

    @Test
    public void testParsePoint() {
        Board board = new Board();

        assertEquals(new Coordinate(0, 0), board.parsePoint("A1"));
        assertEquals(new Coordinate(4, 6), board.parsePoint("G5"));
        assertEquals(new Coordinate(9, 9), board.parsePoint("J10"));
        assertNull(board.parsePoint("AA1"));
        assertNull(board.parsePoint("A0"));
        assertNull(board.parsePoint("K1"));
    }

    @Test
    public void testIsFree() {
        Board board = new Board();
        board.cells[0][0] = GridCellStates.SHIP.ordinal();
        board.cells[3][5] = GridCellStates.SHIP.ordinal();

        assertFalse(board.isFree(new Coordinate(0, 0)));
        assertTrue(board.isFree(new Coordinate(0, 1)));
        assertFalse(board.isFree(new Coordinate(5, 3)));
        assertTrue(board.isFree(new Coordinate(4, 5)));
    }

    @Test
    public void testIsHit() {
        Board board = new Board();
        board.cells[0][0] = GridCellStates.HIT.ordinal();
        board.cells[3][5] = GridCellStates.HIT.ordinal();

        assertTrue(board.isHit(new Coordinate(0, 0)));
        assertFalse(board.isHit(new Coordinate(0, 1)));
        assertTrue(board.isHit(new Coordinate(5, 3)));
        assertFalse(board.isHit(new Coordinate(5, 4)));
    }

    @Test
    public void testIsMiss() {
        Board board = new Board();
        board.cells[0][0] = GridCellStates.MISS.ordinal();
        board.cells[3][5] = GridCellStates.MISS.ordinal();

        assertTrue(board.isMiss(new Coordinate(0, 0)));
        assertFalse(board.isMiss(new Coordinate(0, 1)));
        assertTrue(board.isMiss(new Coordinate(5, 3)));
        assertFalse(board.isMiss(new Coordinate(4, 5)));
    }

    @Test
    public void testIsSunk() {
        Board board = new Board();
        Ship ship1 = new Ship(3, new Coordinate(0, 0), new Coordinate(0, 2));
        Ship ship2 = new Ship(3, new Coordinate(3, 5), new Coordinate(3, 7));

        board.placeShip(ship1);
        board.placeShip(ship2);

        board.cells[0][0] = GridCellStates.HIT.ordinal();
        board.cells[0][1] = GridCellStates.HIT.ordinal();
        board.cells[0][2] = GridCellStates.HIT.ordinal();

        assertFalse(board.isSunk(ship1));
        assertFalse(board.isSunk(ship2));

        board.cells[5][3] = GridCellStates.HIT.ordinal();
        board.cells[6][3] = GridCellStates.HIT.ordinal();
        board.cells[7][3] = GridCellStates.HIT.ordinal();

        assertTrue(board.isSunk(ship2));
    }
}
