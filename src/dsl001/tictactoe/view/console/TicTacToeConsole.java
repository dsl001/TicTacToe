package dsl001.tictactoe.view.console;

import java.util.Scanner;

import dsl001.tictactoe.model.Board;
import dsl001.tictactoe.model.Cell;
import dsl001.tictactoe.model.Cell.Status;
import dsl001.tictactoe.view.TicTacToeView;

/**
 * Console view version.
 * 
 * @author dsl001
 * @since January 2015
 */
public class TicTacToeConsole extends TicTacToeView {
    private final String UNMARKED = " - ";
    private final String X_MARKED = "X";
    private final String O_MARKED = "O";

    // Use scanner to get user input
    private Scanner reader = new Scanner(System.in);

    /**
     * Welcome user to game.
     */
    public TicTacToeConsole() {
        System.out.println("Welcome to Tic Tac Toe!");
    }

    @Override
    public void updateBoard(Cell[][] board) {
        System.out.println();
        System.out.println("    1   2   3  ");
        System.out.println("  -------------");
        for (int i = 0; i < Board.ROWS; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < Board.COLUMNS; j++) {
                System.out.print("| " + getMark(board[i][j].getStatus()) + " ");
            }
            System.out.println("|");
        }
        System.out.println("  -------------");
        System.out.println();
    }

    @Override
    public void notifyWin(boolean firstPlayer) {
        System.out.print("Congratulations Player ");

        if (firstPlayer) {
            System.out.print("1");
        }
        else {
            System.out.print("2");
        }

        System.out.println("!");
        System.out.println("Restarting game...");

        reset();
    }

    @Override
    public void notifyStalemate() {
        System.out.println("Stalemate!");

        reset();
    }

    @Override
    public void notifyTurn(boolean firstPlayer) {
        System.out.println("Enter 'x' to quit or 'r' to restart.");
        System.out.print("Player ");

        if (firstPlayer) {
            System.out.print("1");
        }
        else {
            System.out.print("2");
        }

        System.out.print(", enter mark (row[1-3] column[1-3]): ");

        String input = reader.nextLine();

        if (input.equalsIgnoreCase("x")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        }
        else if (input.equalsIgnoreCase("r")) {
            System.out.println("Restarting game...");
            reset();
        }

        String[] inputs = input.split(" ");

        try {
            if (inputs.length == 2) {
                Integer x = Integer.parseInt(inputs[0].trim());
                Integer y = Integer.parseInt(inputs[1].trim());

                System.out.print("Player ");

                if (firstPlayer) {
                    System.out.print("1");
                }
                else {
                    System.out.print("2");
                }

                System.out.println(" selected [" + x + " " + y + "]");

                playerMarked(x - 1, y - 1);
            }
            else {
                System.out.println("Did not understand input. Try again.");
                notifyTurn(firstPlayer);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Did not understand input. Try again.");
            notifyTurn(firstPlayer);
        }
    }

    @Override
    public void notifyInvalidInput(int r, int c) {
        System.out.println("Invalid user input: row = " + (r + 1)
                + ", column = " + (c + 1) + ". Try again!");
    }

    @Override
    public void notifyCellAlreadyMarked() {
        System.out.println("Cell was already marked. Try again!");
    }

    /**
     * Get status to print.
     * 
     * @param status Status of cell.
     * @return "X" if X_MARK; "O" if O_MARK, "-" if UNMARKED
     */
    private String getMark(Status status) {
        if (status == Status.X_MARKED) {
            return X_MARKED;
        }
        else if (status == Status.O_MARKED) {
            return O_MARKED;
        }

        return UNMARKED;
    }
}
