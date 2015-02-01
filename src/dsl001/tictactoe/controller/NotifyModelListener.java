package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Cell;
import dsl001.tictactoe.view.TicTacToeView;

/**
 * Notify updates to render in model view, TicTacToeView.
 * 
 * @author dsl001
 * @since January 2015
 */
public class NotifyModelListener implements NotifyListener {
    private TicTacToeView view;

    public NotifyModelListener(TicTacToeView view) {
        this.view = view;
    }

    @Override
    public void renderBoard(Cell[][] board) {
        this.view.updateBoard(board);
    }

    @Override
    public void notifyWin(boolean firstPlayer) {
        this.view.notifyWin(firstPlayer);
    }

    @Override
    public void notifyStalemate() {
        this.view.notifyStalemate();
    }

    @Override
    public void notifyTurn(boolean firstPlayer) {
        this.view.notifyTurn(firstPlayer);
    }

    @Override
    public void notifyInvalidInput(int r, int c) {
        this.view.notifyInvalidInput(r, c);
    }

    @Override
    public void notifyCellAlreadyMarked() {
        this.view.notifyCellAlreadyMarked();
    }
}
