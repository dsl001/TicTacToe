package dsl001.tictactoe.model;

/**
 * Model - Cell
 * 
 * @author dsl001
 *
 */
public class Cell {
	public enum Status {
		UNMARKED, X_MARKED, O_MARKED
	};

	private Status status;

	public Cell() {
		setStatus(Status.UNMARKED);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void mark(boolean firstPlayer) {
		if (firstPlayer) {
			setStatus(Status.X_MARKED);
		}
		else {
			setStatus(Status.O_MARKED);
		}
	}
}
