package telosmatter.maxnj;

import telosmatter.maxnj.core.UtilityVector;

/**
 * The actual max-n algorithm.
 * You get your best move
 * of type <code>M</code>
 * for a player of type <code>P</code>
 * from here.
 *
 * <br>
 * <a href="https://github.com/telos-matter/Max-nJ">Github link.</a>
 * @author telos_matter
 *
 * @param <P> Player type
 * @param <M> Move type
 * @param <G> GameState implementation type
 */
public class MaxN <P, M, G extends GameState <P, M, G>> {

    // TODO maybe add time limit to stop and exit?
    // TODO maybe threads?

    /**
     * The evaluationFunction to be used by default
     */
    private Evaluation <P, M, G> evaluationFunction;

    /**
     * The depth to be used by default.
     * Represents how many turns ahead should
     * the algorithm look.
     * Know that one more depth level
     * does not mean that it's the same
     * player's turn again ,rather, it
     * means it's the next player's turn.
     * Preferably you want to at least look
     * one full turn ahead, i.e. if there
     * are 4 players for example
     * then depth should be 5.
     */
    private int depth;

    /**
     * An instance of the MaxN algorithm
     * is not important. It just allows
     * you to set default values
     * to be used.
     *
     * @param evaluationFunction what will evaluate the moves
     * @param depth how many turns ahead should it look. Must be greater
     *              than or equal to one. Read the
     *              documentation here {@link #depth} for more information
     *              about the depth parameter.
     *              
     * @see #getBestMove(GameState) 
     * @see #getBestMove(GameState, Evaluation, int)
     */
    public MaxN (Evaluation <P, M, G> evaluationFunction, int depth) {
        if (depth < 1) {
            throw new IllegalArgumentException("Depth must be greater than or equal to 1!");
        }

        this.evaluationFunction = evaluationFunction;
        this.depth = depth;
    }

    /**
     * Calls {@link #getBestMove(GameState, Evaluation, int)}
     * with the default values.
     */
    public M getBestMove (G gameState) {
        return getBestMove(gameState, evaluationFunction, depth);
    }

    /**
     * Get the best move
     * to play in this <code>gameState</code>.
     *
     * @param gameState the current game state
     * @param evaluationFunction the function that evaluates game states
     * @param depth how far ahead should it look. Must be greater
     *              than or equal to <code>1</code>. Read the
     *              documentation here {@link #depth} for more information
     *              about the depth parameter.
     *
     * @return the best move <code>M</code> to play.
     *
     * @param <P> Player
     * @param <M> Move
     * @param <G> The GameState implementation
     */
    public static <P, M, G extends GameState <P, M, G>> M getBestMove (
            G gameState,
            Evaluation <P, M, G> evaluationFunction,
            int depth) {

        if (depth < 1) {
            throw new IllegalArgumentException("Depth must be greater than or equal to 1!");
        }

        var utilityVector = maxN(gameState, evaluationFunction, depth);
        return utilityVector.getMove(gameState.getPlayer());
    }

    /**
     * Where the algorithm actually goes brrrrr
     * TODO add pruning
     */
    private static <P, M, G extends GameState<P, M, G>> UtilityVector<P, M> maxN (
            G gameState,
            Evaluation <P, M, G> evaluationFunction,
            int depth) {

        // The base case: if we are at a leaf, or
        // the game is over, then return a new
        // utility vector that has the
        // evaluation of this
        // game state for all the player
        if (depth == 0 || gameState.isGameOver()) {
            var utilityVector = new UtilityVector<P, M>();

            for (P player : gameState.getPlayers()) {
                double evaluation = evaluationFunction.evaluate(gameState, player);
                utilityVector.put(player, evaluation);
            }

            return utilityVector;
        }

        // Propagate the utility vectors back up the tree:
        // At each node, the player whose turn it is will select the branch
        // that gives them the highest utility.
        UtilityVector <P, M> bestUtility = null;
        final var player = gameState.getPlayer();
        // Iterate over the moves
        for (M move : gameState.getPossibleMoves()) {
            // Get this move's utility
            var utility = maxN(
                    gameState.playMove(move),
                    evaluationFunction,
                    depth -1);

            // Set the move that will eventually lead to this utility
            utility.updateMove(player, move);

            // Pick the best utility
            bestUtility = UtilityVector.pick(player, bestUtility, utility);
        }

        // Finally return the utility
        // that will now contain the move
        // that would lead to the best utility
        return bestUtility;
    }

    public Evaluation <P, M, G> getEvaluationFunction () {
        return evaluationFunction;
    }

    public void setEvaluationFunction (Evaluation <P, M, G> evaluationFunction) {
        this.evaluationFunction = evaluationFunction;
    }

    public int getDepth () {
        return depth;
    }

    public void setDepth (int depth) {
        this.depth = depth;
    }

}
