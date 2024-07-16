package telosmatter.maxnj;

/**
 * The evaluation function that you
 * need to define / focus on.
 */
@FunctionalInterface
public interface Evaluation <P, M, G extends GameState <P, M, G>> {

    /**
     * Evaluate this <code>gameState</code>
     * for / from the perspective
     * of this <code>player</code>.
     *
     * @param gameState the game state to evaluate.
     * @param player the player for whom the game state
     *               is evaluated.
     *
     * @return the game state evaluation for the given
     * player. The higher, the better that game
     * state is, and thus the better the move that leads to it is.
     */
    public abstract double evaluate (G gameState, P player);

}
