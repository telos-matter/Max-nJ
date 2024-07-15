package telosmatter.maxnj;

@FunctionalInterface
public interface Evaluation {

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
    public abstract double evaluateMove (Move move, Player player, GameState gameState);

}
