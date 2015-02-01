package dsl001.tictactoe.model;

/**
 * Model of cell.
 * 
 * @author dsl001
 * @since January 2015
 */
public class Cell {
    // Different possible status of the cell
    public enum Status {
        UNMARKED, X_MARKED, O_MARKED
    };

    private Status status;

    /**
     * Initialize cell with unmarked status.
     */
    public Cell() {
        reset();
    }

    /**
     * @return Status of cell.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status to unmarked.
     */
    public void reset() {
        this.status = Status.UNMARKED;
    }

    /**
     * Marks with X or O status based on if the player is first or second.
     * 
     * @param firstPlayer True, then set status to X mark; false, then set
     *        status to O mark
     */
    public void mark(boolean firstPlayer) {
        if (firstPlayer) {
            this.status = Status.X_MARKED;
        }
        else {
            this.status = Status.O_MARKED;
        }
    }
}
