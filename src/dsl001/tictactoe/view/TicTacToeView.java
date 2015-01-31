package dsl001.tictactoe.view;

import java.util.ArrayList;
import java.util.List;

import dsl001.tictactoe.controller.PlayerListener;
import dsl001.tictactoe.controller.ResetListener;
import dsl001.tictactoe.controller.ResetModelListener;
import dsl001.tictactoe.model.Cell;

/**
 * View - Abstract class to extend to different types of views (e.g., console,
 * GUI, etc.)
 * 
 * @author dsl001
 *
 */
public abstract class TicTacToeView {
	private List<ResetListener> resetListeners;
	private List<PlayerListener> playerListeners;

	public abstract void updateBoard(Cell[][] board);

	public abstract void notifyWin(boolean firstPlayer);

	public abstract void notifyStalemate();

	public abstract void notifyInvalidInput(int r, int c);

	public abstract void notifyCellAlreadyMarked();

	public abstract void notifyTurn(boolean firstPlayer);

	public TicTacToeView() {
		resetListeners = new ArrayList<ResetListener>();
		playerListeners = new ArrayList<PlayerListener>();
	}

	public void addPlayerListener(PlayerListener playerListener) {
		this.playerListeners.add(playerListener);
	}

	public void addResetListener(ResetModelListener resetListener) {
		this.resetListeners.add(resetListener);
	}

	public void reset() {
		for (ResetListener listener : resetListeners) {
			listener.reset();
		}
	}

	public void playerMarked(int r, int c) {
		for (PlayerListener listener : playerListeners) {
			listener.playerMarked(r, c);
		}
	}
}
