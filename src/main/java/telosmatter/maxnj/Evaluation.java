package telosmatter.maxnj;

/**
 * The evaluation function that you
 * need to define.
 */
@FunctionalInterface
public interface Evaluation <P, M> {

    /**
     * Evaluate this <code>move</code>
     * that this <code>player</code>
     * could take, that would result
     * in this <code>gameState</code>.
     *
     * @param move that could be taken
     * @param player the player that wants to take the move. The one this move
     *               should be evaluated for
     * @param gameState that the game will be in, if the player made this move
     *
     * @return the move evaluation for the given
     * player. The higher, the better the move is.
     */
    public abstract double evaluateMove (M move, P player, GameState <P, M> gameState);

}
