package tictactoe;

import org.junit.jupiter.api.Test;
import telosmatter.maxnj.MaxN;
import tictactoe.game.Board;
import tictactoe.game.XO;
import tictactoe.maxNImpl.BoardEvaluation;
import tictactoe.maxNImpl.BoardState;

import java.util.Scanner;

public class TicTacToeTest {

    /**
     * This is not really a test, more of like a showcase
     */
    @Test
    public void BasicTest () {
        BoardState boardState = new BoardState(new Board());

        while (!boardState.isGameOver()) {
            System.out.printf("It's %s turn!%n", boardState.getPlayer());
            System.out.println(boardState.board());

            Integer bestMove = MaxN.getBestMove(
                    boardState,
                    BoardEvaluation.evaluationFunction,
                    3);

            System.out.printf("Playing %d%n", bestMove);
            boardState = boardState.playMove(bestMove);
            System.out.println();
        }

        System.out.println(boardState.board());
        System.out.printf("Game is over. Winner is: %s%n", boardState.board().winner);
    }

    // Run this if YOU want ot play and get a tie no matter what, lol.
//    public static void main(String[] args) {
//        Scanner kyb = new Scanner(System.in);
//
//        BoardState boardState = new BoardState(new Board());
//        XO player = boardState.getPlayer();
//
//        while (!boardState.isGameOver()) {
//            System.out.printf("It's %s turn!%n", boardState.getPlayer());
//            System.out.println(boardState.board());
//
//            Integer move = null;
//
//            if (player == boardState.getPlayer()) {
//                System.out.println("Which cell to play?");
//                move = kyb.nextInt();
//
//            } else {
//                move = MaxN.getBestMove(
//                        boardState,
//                        BoardEvaluation.evaluationFunction,
//                        100);
//            }
//
//            System.out.printf("Playing %d%n", move);
//            boardState = boardState.playMove(move);
//            System.out.println();
//        }
//
//        System.out.println(boardState.board());
//        System.out.printf("Game is over. Winner is: %s%n", boardState.board().winner);
//    }

}
