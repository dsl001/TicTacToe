package dsl001.tictactoe.controller;

import java.util.Scanner;

import dsl001.tictactoe.view.console.TicTacToeConsole;
import dsl001.tictactoe.view.gui.TicTacToeGui;

public class TicTacToeTest {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        Scanner reader = new Scanner(System.in);

        System.out.println("Select the following view...");
        System.out.println("  (1) Console");
        System.out.println("  (2) GUI");
        System.out.print("User input: ");

        int input = reader.nextInt();

        if (input == 1) {
            System.out.println("Launching console version...");
            game.startGame(new TicTacToeConsole());
        }
        else {
            System.out.println("Launching GUI version...");
            game.startGame(new TicTacToeGui());
        }
    }
}
