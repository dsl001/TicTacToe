package dsl001.tictactoe.view.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dsl001.tictactoe.model.Board;
import dsl001.tictactoe.model.Cell;
import dsl001.tictactoe.model.Cell.Status;
import dsl001.tictactoe.view.TicTacToeView;

/**
 * GUI version of view, using Swing.
 * 
 * @author dsl001
 * @since January 2015
 */
public class TicTacToeGui extends TicTacToeView {
    private final String UNMARKED = " - ";
    private final String X_MARKED = "X";
    private final String O_MARKED = "O";

    private JFrame frame = new JFrame();
    private JLabel lblStatus = new JLabel();

    // JButton array to represent cells
    private JButton[][] cells;

    /**
     * Initialize cells and set up board.
     */
    public TicTacToeGui() {
        cells = new JButton[Board.ROWS][Board.COLUMNS];
        JPanel pnlBoard = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;

        for (int r = 0; r < Board.ROWS; r++) {
            gbc.gridx = r;
            int row = r;

            for (int c = 0; c < Board.COLUMNS; c++) {
                gbc.gridy = c;

                int col = c;

                JButton btnCell = new JButton(UNMARKED);
                btnCell.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        playerMarked(row, col);
                    }
                });

                pnlBoard.add(btnCell, gbc);
                cells[r][c] = btnCell;
            }
        }

        gbc.gridx = 0;
        gbc.gridy = Board.ROWS + 1;
        gbc.gridwidth = 3;
        pnlBoard.add(lblStatus, gbc);

        frame.add(pnlBoard);
        frame.pack();
        frame.setSize(150, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void updateBoard(Cell[][] board) {
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                if (board[r][c].getStatus() != Status.UNMARKED) {
                    if (board[r][c].getStatus() == Status.X_MARKED) {
                        cells[r][c].setText(X_MARKED);
                    }
                    else {
                        cells[r][c].setText(O_MARKED);
                    }

                    cells[r][c].setEnabled(false);
                }
                else {
                    cells[r][c].setText(UNMARKED);
                    cells[r][c].setEnabled(true);
                }
            }
        }
    }

    @Override
    public void notifyWin(boolean firstPlayer) {
        StringBuilder sb = new StringBuilder("You won, Player ");

        if (firstPlayer) {
            sb.append(1);
        }
        else {
            sb.append(2);
        }

        sb.append("!");

        JOptionPane.showMessageDialog(frame, sb.toString());

        reset();
    }

    @Override
    public void notifyStalemate() {
        JOptionPane.showMessageDialog(frame, "Stalemate!");

        reset();
    }

    @Override
    public void notifyInvalidInput(int r, int c) {
        JOptionPane.showMessageDialog(frame, "Invalid input!");
    }

    @Override
    public void notifyCellAlreadyMarked() {
        JOptionPane.showMessageDialog(frame, "Already marked!");
    }

    @Override
    public void notifyTurn(boolean firstPlayer) {
        StringBuilder sb = new StringBuilder("Your turn, Player ");

        if (firstPlayer) {
            sb.append(1);
        }
        else {
            sb.append(2);
        }

        lblStatus.setText(sb.toString());
    }

}
