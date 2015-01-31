package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Board;
import dsl001.tictactoe.view.TicTacToeView;

/**
 * Controller
 * 
 * @author dsl001
 * 
 */
public class TicTacToe {
	private Board model;

	public TicTacToe() {
		this.model = new Board();
	}

	public void startGame(TicTacToeView view) {
		NotifyListener notifyListener = new NotifyModelListener(view);
		model.addNotifyListener(notifyListener);

		ResetModelListener resetListener = new ResetModelListener(model);
		view.addResetListener(resetListener);

		PlayerListener playerListener = new PlayerModelListener(model);
		view.addPlayerListener(playerListener);

		model.reset();
	}
}
