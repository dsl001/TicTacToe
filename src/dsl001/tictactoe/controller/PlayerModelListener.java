package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Board;

public class PlayerModelListener implements PlayerListener {

	private Board model;
	
	public PlayerModelListener(Board model) {
		this.model = model;
	}

	@Override
	public void playerMarked(int r, int c) {
		this.model.placeMark(r, c);
	}

}
