package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Board;

/**
 * Resets the model.
 * 
 * @author dsl001
 * @since January 2015
 */
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
