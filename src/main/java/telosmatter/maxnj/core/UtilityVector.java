package telosmatter.maxnj.core;

import telosmatter.maxnj.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * The utility vector used by MaxN algorithm.
 *
 * @param <P> Player type
 * @param <M> Move type
 */
public class UtilityVector <P, M> {

    /**
     * Maps each player to his utility
     * alongside the move that
     * would eventually lead to that
     * utility.
     */
    Map <P, Pair <M, Double>> utilities;

    public UtilityVector () {
        utilities = new HashMap<>();
    }

    /**
     * @return the existing pair mapped to this player.
     */
    private Pair <M, Double> get (P player) {
        var pair = utilities.get(player);
        if (pair == null) {
            // It's IllegalState and not IllegalArgument
            // because it should not be called in this state
            // where this player does not exist.
            throw new IllegalStateException("The utility for %s does not exist!".formatted(player));
        }
        return pair;
    }

    /**
     * Map this player to this
     * utility. Move will
     * be <code>null</code>.
     */
    public void put (P player, double value) {
        utilities.put(player, new Pair<>(null, value));
    }

    /**
     * Set the move
     * that will eventually lead
     * to the utility that the
     * given player has.
     */
    public void updateMove (P player, M move) {
        var pair = get(player);
        pair.first = move;
    }

    /**
     * @return the utility
     * mapped to the given
     * player.
     *
     * @see #pick(Object, UtilityVector, UtilityVector)
     */
    public double getUtility (P player) {
        var pair = get(player);
        return pair.second;
    }

    /**
     * @return the move that will eventually
     * lead to the utility mapped
     * to the given player
     */
    public M getMove (P player) {
        var pair = get(player);
        return pair.first;
    }

    /**
     * Picks the utility vector
     * with the highest utility
     * for the given player.
     * Please give bestVec first
     *
     * @param utilityVectorA if either vectors is null then
     *                       this one please
     */
    public static <P, M> UtilityVector <P, M> pick (
            P player,
            UtilityVector <P, M> utilityVectorA,
            UtilityVector <P, M> utilityVectorB) {

        // First one is the one usually to
        // be null, so check Second one first as to short circuit
        if (utilityVectorB == null && utilityVectorA == null) {
            throw new IllegalArgumentException("Both utility vectors cannot be null!");
        }

        // If either is null, return the other
        if (utilityVectorA == null) {
            return utilityVectorB;
        } else if (utilityVectorB == null) {
            return utilityVectorA;
        }

        // Otherwise return the one with the highest
        // utility for the given player
        final double utilityA = utilityVectorA.getUtility(player);
        final double utilityB = utilityVectorB.getUtility(player);
        return (utilityA > utilityB)? utilityVectorA : utilityVectorB;
    }

}
