package dsl001.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

import dsl001.tictactoe.controller.NotifyListener;
import dsl001.tictactoe.model.Cell.Status;

/**
 * Model of the board.
 * 
 * @author dsl001
 * @since January 2015
 */
public class Board {
    public static final int ROWS = 3; // Number of rows on the board
    public static final int COLUMNS = 3; // Number of columns on the board

    // Number of total moves it takes to start checking if a player won
    public static final int NUM_MOVES_TO_CHECK_WIN = 5;

    // Toggles between first and second player
    private boolean firstPlayer;

    // Board comprised of double array of cells
    private Cell[][] board;

    // Track number of moves
    private int numMoves;

    // Whether three in a row has been identified
    private boolean playerWon;

    // List of notify listeners
    private List<NotifyListener> notifyListeners;

    /**
     * Initializes the listeners and board.
     */
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

    /**
     * @return Status of all cells on the board.
     */
    public Cell[][] getStatus() {
        return board;
    }

    /**
     * Resets the board to start a new game.
     */
    public void reset() {
        // Set first player to mark first
        firstPlayer = true;
        // Reset number of moves made
        numMoves = 0;
        // Set playerWon to false
        playerWon = false;

        // Mark all the cells to unmarked status
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                board[r][c].reset();
            }
        }

        // Notify that the board needs to be rendered
        renderBoard(board);
        // Notify to place mark
        notifyPlayer();
    }

    /**
     * Checks if player won by checking for three of the same status.
     * 
     * @param r Row position to start checking for win
     * @param c Column position to start checking for win
     */
    public void setPlayerWon(int r, int c) {
        // Check for three in in a row
        boolean row = board[r][0].getStatus() == board[r][1].getStatus()
                && board[r][1].getStatus() == board[r][2].getStatus();

        // Check for three in a column
        boolean column = board[0][c].getStatus() == board[1][c].getStatus()
                && board[1][c].getStatus() == board[2][c].getStatus();

        // Check for three in a backwards diagonal
        boolean bdiagonal = (r == c)
                && board[0][0].getStatus() == board[1][1].getStatus()
                && board[1][1].getStatus() == board[2][2].getStatus();

        // Check for three in a forwards diagonal
        boolean fdiagonal = (r + c == 2)
                && board[2][0].getStatus() == board[1][1].getStatus()
                && board[1][1].getStatus() == board[0][2].getStatus();

        playerWon = row || column || bdiagonal || fdiagonal;
    }

    /**
     * Update status on cell on the board.
     * 
     * @param r Row position to update
     * @param c Column position to update
     */
    public void placeMark(int r, int c) {
        // Only update if row and column position are valid
        if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS) {
            // Only update if cell is unmarked
            if (board[r][c].getStatus() == Status.UNMARKED) {
                board[r][c].mark(firstPlayer);

                // With mark mark, render board
                renderBoard(board);

                // Check for win if number of moves are made to check for win
                if ((numMoves + 1) >= NUM_MOVES_TO_CHECK_WIN) {
                    setPlayerWon(r, c);

                    // Notify if a won occurred
                    if (playerWon) {
                        notifyWin();
                    }
                    // If no win occurred and all moves have been made on the
                    // board, notify a stalemate occurred
                    else if ((numMoves + 1 == (ROWS * COLUMNS))) {
                        notifyStalemate();
                    }
                    // If not win or stalemate occurred, notify next player to
                    // mark
                    else {
                        firstPlayer = !firstPlayer;
                        numMoves++;

                        notifyPlayer();
                    }
                }
                // Notify next player to mark
                else {
                    firstPlayer = !firstPlayer;
                    numMoves++;

                    notifyPlayer();
                }
            }
            // Notify player that the cell was already marked
            else {
                notifyCellAlreadyMarked();
                // renderBoard(board);
                notifyPlayer();
            }
        }
        // Notify player that the mark position is invalid
        else {
            notifyInvalidInput(r, c);
            // renderBoard(board);
            notifyPlayer();
        }
    }

    /**
     * Goes through list of notify listeners to callback with board status per
     * cell.
     */
    private void renderBoard(Cell[][] board) {
        for (NotifyListener listener : notifyListeners) {
            listener.renderBoard(board);
        }
    }

    /**
     * Goes through list of notify listeners to callback and notify win.
     */
    private void notifyWin() {
        for (NotifyListener listener : notifyListeners) {
            listener.notifyWin(firstPlayer);
        }
    }

    /**
     * Goes through list of notify listeners to callback and notify stalemate.
     */
    private void notifyStalemate() {
        for (NotifyListener listener : notifyListeners) {
            listener.notifyStalemate();
        }
    }

    /**
     * Goes through list of notify listeners to callback and notify turn of
     * player.
     */
    private void notifyPlayer() {
        for (NotifyListener listener : notifyListeners) {
            listener.notifyTurn(firstPlayer);
        }

    }

    /**
     * Goes through list of notify listeners to callback and notify invalid
     * input.
     * 
     * @param r Row value of invalid position
     * @param c Column value of invalid position
     */
    private void notifyInvalidInput(int r, int c) {
        for (NotifyListener listener : notifyListeners) {
            listener.notifyInvalidInput(r, c);
        }
    }

    /**
     * Goes through list of notify listeners to callback and notify that the
     * cell is already marked.
     */
    private void notifyCellAlreadyMarked() {
        for (NotifyListener listener : notifyListeners) {
            listener.notifyCellAlreadyMarked();
        }
    }

    /**
     * Add notify listener to list.
     * 
     * @param listener Listener to add
     */
    public void addNotifyListener(NotifyListener listener) {
        this.notifyListeners.add(listener);
    }
}
