package dsl001.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

import dsl001.tictactoe.controller.NotifyListener;
import dsl001.tictactoe.model.Cell.Status;

/**
 * Model - Board
 * 
 * @author dsl001
 *
 */
public class Board {
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;

	public static final int NUM_MOVES_TO_CHECK_WIN = 5;

	private boolean firstPlayer;

	private Cell[][] board;

	private int numMoves;

	private boolean playerWon;

	private List<NotifyListener> notifyListeners;

	public Board() {
		notifyListeners = new ArrayList<NotifyListener>();

		board = new Cell[ROWS][COLUMNS];

		// initialize board
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				board[r][c] = new Cell();
			}
		}
	}

	public Cell[][] getStatus() {
		return board;
	}

	public void reset() {
		firstPlayer = true;
		numMoves = 0;
		playerWon = false;

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				board[r][c].setStatus(Status.UNMARKED);
			}
		}

		renderBoard(board);
		notifyPlayer();
	}

	private void notifyPlayer() {
		for (NotifyListener listener : notifyListeners) {
			listener.notifyTurn(firstPlayer);
		}

	}

	private void renderBoard(Cell[][] board) {
		for (NotifyListener listener : notifyListeners) {
			listener.renderBoard(board);
		}
	}

	public void setPlayerWon(int r, int c) {
		boolean row = board[r][0].getStatus() == board[r][1].getStatus()
				&& board[r][1].getStatus() == board[r][2].getStatus();

		boolean column = board[0][c].getStatus() == board[1][c].getStatus()
				&& board[1][c].getStatus() == board[2][c].getStatus();

		boolean bdiagonal = (r == c)
				&& board[0][0].getStatus() == board[1][1].getStatus()
				&& board[1][1].getStatus() == board[2][2].getStatus();

		boolean fdiagonal = (r + c == 2)
				&& board[2][0].getStatus() == board[1][1].getStatus()
				&& board[1][1].getStatus() == board[0][2].getStatus();

		playerWon = row || column || bdiagonal || fdiagonal;
	}

	public void placeMark(int r, int c) {
		if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS) {
			if (board[r][c].getStatus() == Status.UNMARKED) {
				board[r][c].mark(firstPlayer);

				renderBoard(board);

				if ((numMoves + 1) >= NUM_MOVES_TO_CHECK_WIN) {
					setPlayerWon(r, c);

					if (playerWon) {
						notifyWin();
					}
					else if ((numMoves + 1 == (ROWS * COLUMNS))) {
						notifyStalemate();
					}
					else {
						firstPlayer = !firstPlayer;
						numMoves++;

						notifyPlayer();
					}
				}
				else {
					firstPlayer = !firstPlayer;
					numMoves++;

					notifyPlayer();
				}
			}
			else {
				notifyCellAlreadyMarked();
				notifyPlayer();
				renderBoard(board);
			}
		}
		else {
			notifyInvalidInput(r, c);
			notifyPlayer();
			renderBoard(board);
		}
	}

	private void notifyInvalidInput(int r, int c) {
		for (NotifyListener listener : notifyListeners) {
			listener.notifyInvalidInput(r, c);
		}
	}

	private void notifyCellAlreadyMarked() {
		for (NotifyListener listener : notifyListeners) {
			listener.notifyCellAlreadyMarked();
		}
	}

	private void notifyStalemate() {
		for (NotifyListener listener : notifyListeners) {
			listener.notifyStalemate();
		}
	}

	private void notifyWin() {
		for (NotifyListener listener : notifyListeners) {
			listener.notifyWin(firstPlayer);
		}
	}

	public void addNotifyListener(NotifyListener listener) {
		this.notifyListeners.add(listener);
	}

	public boolean isPlayerWon() {
		return playerWon;
	}
}
