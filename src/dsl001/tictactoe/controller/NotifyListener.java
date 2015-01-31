package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Cell;

public interface NotifyListener {
	public abstract void renderBoard(Cell[][] board);
	public abstract void notifyWin(boolean firstPlayer);
	public abstract void notifyStalemate();
	public abstract void notifyTurn(boolean firstPlayer);
	public abstract void notifyInvalidInput(int r, int c);
	public abstract void notifyCellAlreadyMarked();
}
