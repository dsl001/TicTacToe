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
 * @since January 2015
 */
public abstract class TicTacToeView {
    private List<ResetListener> resetListeners; // List of reset listeners
    private List<PlayerListener> playerListeners; // List of player listeners

    /**
     * Updates the view of the board based on cell status.
     * 
     * @param board Status of cells
     */
    public abstract void updateBoard(Cell[][] board);

    /**
     * Notify a player won.
     * 
     * @param firstPlayer True, if first player won; false, if second player
     *        won.
     */
    public abstract void notifyWin(boolean firstPlayer);

    /**
     * Notify stalemate.
     */
    public abstract void notifyStalemate();

    /**
     * Notify invalid position.
     * 
     * @param r Row value of invalid position
     * @param c Column value of invalid position
     */
    public abstract void notifyInvalidInput(int r, int c);

    /**
     * Notify cell is already marked.
     */
    public abstract void notifyCellAlreadyMarked();

    /**
     * Notify the turn of the player.
     * 
     * @param firstPlayer True, if first player's turn; false, if second
     *        player's turn.
     */
    public abstract void notifyTurn(boolean firstPlayer);

    /**
     * Initialize list of reset and player listeners.
     */
    public TicTacToeView() {
        resetListeners = new ArrayList<ResetListener>();
        playerListeners = new ArrayList<PlayerListener>();
    }

    /**
     * Add player listener to list.
     * 
     * @param playerListener Listener to add
     */
    public void addPlayerListener(PlayerListener playerListener) {
        this.playerListeners.add(playerListener);
    }

    /**
     * Add reset listener to list.
     * 
     * @param resetListener Listener to add
     */
    public void addResetListener(ResetModelListener resetListener) {
        this.resetListeners.add(resetListener);
    }

    /**
     * Goes through list of notify listeners to callback to reset game.
     */
    public void reset() {
        for (ResetListener listener : resetListeners) {
            listener.reset();
        }
    }

    /**
     * Goes through list of notify listeners to callback and change status of
     * cell.
     * 
     * @param r Row value of position
     * @param c Column value of position
     */
    public void playerMarked(int r, int c) {
        for (PlayerListener listener : playerListeners) {
            listener.playerMarked(r, c);
        }
    }
}
