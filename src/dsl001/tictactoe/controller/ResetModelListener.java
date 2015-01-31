package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Board;

public class ResetModelListener implements ResetListener {
	private Board model;
	
	public ResetModelListener(Board model) {
		this.model = model;
	}
	
	@Override
	public void reset() {
		this.model.reset();
	}
}
