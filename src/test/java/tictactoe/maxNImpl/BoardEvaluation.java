package tictactoe.maxNImpl;

import telosmatter.maxnj.Evaluation;
import tictactoe.game.Board;
import tictactoe.game.XO;

import java.util.Arrays;
import java.util.List;

public class BoardEvaluation {

    public static final Evaluation <XO, Integer, BoardState> evaluationFunction = new Evaluation<>() {
        @Override
        public double evaluate(BoardState gameState, XO player) {
            final Board board = gameState.board();

            // Check if the game is over
            if (board.isGameOver()) {
                // If so, return value accordingly
                if (board.winner == null) {
                    return 6969; // A tie
                } else if (board.winner == player) {
                    return Double.POSITIVE_INFINITY; // Player won
                } else {
                    return Double.NEGATIVE_INFINITY; // Player lost
                }
            }

            // Game is not yet over.
            // The value of the board is
            // how many two in a row we have
            // minus how many two in a row the
            // other has
            double value = 0;
            for (int[] triple : Board.TRIPLETS) {
                if (twoOneEmpty(board, player, triple)) {
                    value += 10;
                } else if (twoOneEmpty(board, player.other(), triple)) {
                    value -= 10;
                }
            }
            return value;
        }
    };

    /**
     * Checks if the given triplet has two of the specified player's markers
     * and an empty cell
     *
     * @param board the board to check
     * @param player the player to check for
     * @param triple the triplet to check
     * @return true if the triplet has two of the
     * player's markers and an empty cell
     */
    private static boolean twoOneEmpty(Board board, XO player, int[] triple) {
        int c1 = triple[0], c2 = triple[1], c3 = triple[2];
        XO[] cs = board.cells;
        List<XO> tripletCells = Arrays.asList(cs[c1], cs[c2], cs[c3]);

        int count = 0;
        for (XO cell : tripletCells) {
            if (cell == player) {
                count++;
            } else if (cell == null) {
                // Do nothing
            } else {
                count--;
            }
        }
        return count == 2;
    }

}
