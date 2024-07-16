package telosmatter.maxnj;

import java.util.Collection;

/**
 * A state of a "<code>G</code>
 * type of Game", that is
 * played by <code>P</code> type
 * of Players, through <code>M</code>
 * type of Moves.
 * Represents a node
 * in the game tree.
 *
 * @param <P> Player type
 * @param <M> Move type
 * @param <G> The GameState implementation
 */
public interface GameState <P, M, G extends GameState <P, M, G>> {

    /**
     * Give me all the players that are playing
     * in this game.
     */
    public abstract Collection <P> getPlayers ();

    /**
     * Give me the player that will play in
     * this current game state. The one
     * who will make a move. The player
     * whose turn is it.
     */
    public abstract P getPlayer ();

    /**
     * Give me all the possible moves that can
     * be taken in this current game state.
     */
    public abstract Collection <M> getPossibleMoves ();

    /**
     * Give me the game state that
     * will result if this move was
     * played.
     * This should not modify your actual
     * game, you should rather make a copy
     * of the game and play on the copy.
     */
    public abstract G playMove (M move);

    /**
     * Is the game over in this state?
     */
    public abstract boolean isGameOver ();

}
