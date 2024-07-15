package telosmatter.maxnj;

import java.util.Collection;

/**
 * A state of a game that is
 * played by <code>P</code> type
 * of Players, through <code>M</code>
 * type of Moves.
 * Represents a node
 * in the game tree.
 */
public interface GameState <P, M> {

    /**
     * Give me the player that will play in
     * this current game state. The one
     * who will make a move. The player
     * whose turn is it.
     */
    public abstract P getPlayer ();

    /**
     * Give me all the possible moves that can
     * be taken in this current game state
     */
    public abstract Collection <M> getPossibleMoves ();

    /**
     * Give me the game state that
     * will result from playing this move
     * in this current game state
     */
    public abstract GameState <P, M> playMove (M move);

    /**
     * Is the game over in this state?
     */
    public abstract boolean isGameOver ();

}
