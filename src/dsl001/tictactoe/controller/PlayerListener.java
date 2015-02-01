package dsl001.tictactoe.controller;

/**
 * Interface of interaction of player.
 * 
 * @author dsl001
 * @since January 2015
 */
public interface PlayerListener {
    /**
     * Player marked on the cell.
     * 
     * @param r Row value of cell marked
     * @param c Column value of cell marked
     */
    public abstract void playerMarked(int r, int c);
    
    
}
