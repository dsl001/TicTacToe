package dsl001.tictactoe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dsl001.tictactoe.model.BoardTest;
import dsl001.tictactoe.model.CellTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BoardTest.class, CellTest.class })

public class TicTacToeTestSuite {
    
}