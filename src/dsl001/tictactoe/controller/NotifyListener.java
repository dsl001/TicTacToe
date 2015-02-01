package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Cell;

/**
 * Interface to notify changes, such as a change on the board, win, stalemate,
 * etc.
 * 
 * @author dsl001
 * @since January 2015
 */
public interface NotifyListener {
	/**
	 * Current state of board to render.
	 * 
	 * @param board Gives information of each state of cell on the board
	 */
	public abstract void renderBoard(Cell[][] board);

	/**
	 * Notifies if a win.
	 * 
	 * @param firstPlayer True, if first play won; false, if second player won
	 */
	public abstract void notifyWin(boolean firstPlayer);

	/**
	 * Notifies if there are no more moves available for player one or two to
	 * win.
	 */
	public abstract void notifyStalemate();

	/**
	 * Notifies turn on player.
	 * 
	 * @param firstPlayer True, if first player's turn; false, if second
	 *        player's turn
	 */
	public abstract void notifyTurn(boolean firstPlayer);

	/**
	 * Notifies if player marked an invalid cell position.
	 * 
	 * @param r Row value of the invalid position
	 * @param c Column value of the invalid position
	 */
	public abstract void notifyInvalidInput(int r, int c);

	    /**
     * Notifies if the cell player attempted to mark is already marked.
     */
    public abstract void notifyCellAlreadyMarked();
}
