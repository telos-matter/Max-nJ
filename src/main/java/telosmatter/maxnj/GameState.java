package telosmatter.maxnj;

import java.util.Collection;

/**
 * The state of a game. A node
 * in the game tree.
 */
public interface GameState {

    /**
     * Give me the player that will play in
     * this current game state. The one
     * who will make a move.
     */
    public abstract Player getPlayer ();

    /**
     * Give me all the possible moves that can
     * be taken in this current game state
     */
    public abstract Collection <Move> getPossibleMoves ();

    /**
     * Give me the game state that
     * will result from playing this move
     * in this current game state
     */
    public abstract GameState playMove (Move move);

    /**
     * Is the game over in this state?
     */
    public abstract boolean isGameOver ();



}
