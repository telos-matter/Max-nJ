package tictactoe.maxNImpl;

import telosmatter.maxnj.GameState;
import tictactoe.game.Board;
import tictactoe.game.XO;

import java.util.Collection;

/**
 * @param board The board at the current state
 */
public record BoardState (Board board) implements GameState<XO, Integer, BoardState> {

    @Override
    public Collection<XO> getPlayers() {
        return XO.VALUES;
    }

    @Override
    public XO getPlayer() {
        return board.turn;
    }

    @Override
    public Collection<Integer> getPossibleMoves() {
        return board.getPossibleMoves();
    }

    @Override
    public BoardState playMove(Integer move) {
        Board next = board.fakePlay(move);
        return new BoardState(next);
    }

    @Override
    public boolean isGameOver() {
        return board.isGameOver();
    }
}
