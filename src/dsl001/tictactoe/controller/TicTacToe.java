package dsl001.tictactoe.controller;

import dsl001.tictactoe.model.Board;
import dsl001.tictactoe.view.TicTacToeView;

/**
 * Controller that manages the model and view.
 * 
 * @author dsl001
 * @since January 2015
 */
public class TicTacToe {
    private Board model;

    /**
     * Sets up the model.
     */
    public TicTacToe() {
        this.model = new Board();
    }

    /**
     * Starts the game based on the view.
     * 
     * @param view View to apply to the game
     */
    public void startGame(TicTacToeView view) {
        // Add the notify listener for the model to interact with the view
        NotifyListener notifyListener = new NotifyModelListener(view);
        model.addNotifyListener(notifyListener);

        // Add the player listener for the view to interact with the model,
        // based on player
        PlayerListener playerListener = new PlayerModelListener(model);
        view.addPlayerListener(playerListener);

        // Add the reset listener for the view to interact with the model
        ResetModelListener resetListener = new ResetModelListener(model);
        view.addResetListener(resetListener);

        // Reset model to start game
        model.reset();
    }
}
