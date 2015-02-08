package dsl001.tictactoe.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import dsl001.tictactoe.model.Cell.Status;

/**
 * JUnit test for Board class.
 * 
 * @author dsl001
 * @since January 2015
 */
public class BoardTest {
    private Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @After
    public void tearDown() throws Exception {
        board = null;
    }

    @Test
    public void testBoard() {
        // Make sure instantiated class is not null
        assertNotNull(board);
    }

    @Test
    public void testGetStatus() {
        Cell[][] cells = board.getStatus();

        // Make sure getStatus does not return null
        assertNotNull(cells);

        // Make sure each cell is instantiated
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                assertNotNull(cells[r][c]);
            }
        }
    }

    @Test
    public void testReset() {
        // If the board is reseted, then all the status per cell should be
        // unmarked
        board.reset();

        Cell[][] cells = board.getStatus();

        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                assertEquals(Status.UNMARKED, cells[r][c].getStatus());
            }
        }
    }

    @Test
    public void testPlaceMark() {
        // Choose an initial position and get cell
        int r = 0;
        int c = 0;

        Cell cell = board.getStatus()[r][c];

        // Verify that the cell is initially unmarked
        assertEquals(Status.UNMARKED, cell.getStatus());

        // Mark the cell and check status
        board.placeMark(r, c);
        cell = board.getStatus()[r][c];

        // Since the first player is making the mark, the status should be X
        // marked
        assertEquals(Status.X_MARKED, cell.getStatus());

        // Mark the cell in the same position again
        board.placeMark(r, c);
        cell = board.getStatus()[r][c];

        // Verify that the status is still X marked
        assertEquals(Status.X_MARKED, cell.getStatus());

        // Change the position to get access to another cell
        r = 1;

        cell = board.getStatus()[r][c];

        // Verify that the cell is initally unmarked
        assertEquals(Status.UNMARKED, cell.getStatus());

        // Mark the cell and check status
        board.placeMark(r, c);
        cell = board.getStatus()[r][c];

        assertEquals(Status.O_MARKED, cell.getStatus());

        board.placeMark(r, c);
        cell = board.getStatus()[r][c];

        assertEquals(Status.O_MARKED, cell.getStatus());
    }
}
