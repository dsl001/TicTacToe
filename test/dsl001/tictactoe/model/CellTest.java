package dsl001.tictactoe.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dsl001.tictactoe.model.Cell.Status;

/**
 * JUnit test Cell class.
 * 
 * @author dsl001
 * @since January 2015
 */
public class CellTest {
    private Cell cell;

    @Before
    public void setUp() throws Exception {
        cell = new Cell();
    }

    @Test
    public void testCell() {
        assertNotNull(cell);
    }

    @Test
    public void testReset() {
        cell.reset();

        assertEquals(Status.UNMARKED, cell.getStatus());
    }

    @Test
    public void testMark() {
        cell.mark(true);

        assertEquals(Status.X_MARKED, cell.getStatus());

        cell.mark(false);

        assertEquals(Status.O_MARKED, cell.getStatus());
    }
}
