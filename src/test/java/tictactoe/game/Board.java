package tictactoe.game;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents and guides
 * a single game of Tic-Tac-Toe
 */
public class Board implements Cloneable {

    private static final Random RANDOM = new Random();

    public static final List<int[]> TRIPLETS = Arrays.asList(
            new int[]{0, 1, 2}, // First row
            new int[]{3, 4, 5}, // Second row
            new int[]{6, 7, 8}, // Third row
            new int[]{0, 3, 6}, // First column
            new int[]{1, 4, 7}, // Second column
            new int[]{2, 5, 8}, // Third column
            new int[]{0, 4, 8}, // First diagonal
            new int[]{2, 4, 6}  // Second diagonal
    );

    private static int counter = 0;

    public int id;              // This board's id
    public XO[] cells;          // The board cells
    public XO turn;             // Whose turn it is
    public final XO started;    // Who started the game
    public int turnsCount;      // How many turns have been played
    public boolean gameOver;    // Is the game over
    public XO winner;           // The winner of the game
    private Integer firstMove;  // The first move of the game
    public Integer lastMove;    // The last move of the game

    private boolean checkedGameOver;    // If the game over state has been checked

    public Board() {
        counter++;
        this.id = counter;

        this.cells = new XO[9];
        this.turn = XO.randomOne();
        this.started = this.turn;
        this.turnsCount = 0;
        this.gameOver = false;
        this.winner = null;
        this.firstMove = null;
        this.lastMove = null;

        this.checkedGameOver = false;
    }

    /**
     *
     * @return if the game is over
     */
    public boolean isGameOver() {
        if (this.gameOver) {
            return true;
        }

        if (this.checkedGameOver) {
            return false;
        }

        if (this.turnsCount >= 5) {
            for (int[] line : TRIPLETS) {
                int c1 = line[0], c2 = line[1], c3 = line[2];
                if (this.cells[c1] != null && this.cells[c1] == this.cells[c2] && this.cells[c2] == this.cells[c3]) {
                    this.gameOver = true;
                    this.winner = this.cells[c1];
                    break;
                }
            }

            if (!this.gameOver && this.turnsCount == 9) {
                this.gameOver = true;
                this.winner = null;
            }
        }

        this.checkedGameOver = true;
        return this.gameOver;
    }

    public void checkGameNotOver () {
        if (isGameOver()) {
            throw new IllegalStateException("The has already ended! Board ID: %d".formatted(id));
        }
    }

    /**
     * @return a list of all possible moves
     */
    public List<Integer> getPossibleMoves() {
        checkGameNotOver();

        List<Integer> possibleMoves = new ArrayList<>(9);
        for (int i = 0; i < this.cells.length; i++) {
            if (this.cells[i] == null) {
                possibleMoves.add(i + 1);
            }
        }
        return possibleMoves;
    }

    /**
     * Tries to place the current player's marker on the specified cell
     */
    public void playCell(int cell) {
        checkGameNotOver();
        if (cell < 1 || cell > 9) {
            throw new IllegalArgumentException("Trying to play %d, which is outside the board. Board ID: %d".formatted(cell, id));
        }

        if (this.cells[cell -1] != null) {
            throw new IllegalArgumentException("Can't play %d. That cell has already been played".formatted(cell));
        }

        cell -= 1; // For proper indexing
        this.checkedGameOver = false;
        this.turnsCount++;

        if (this.turnsCount == 1) {
            this.firstMove = cell + 1;
        }

        this.cells[cell] = this.turn;

        if (this.isGameOver()) {
            this.lastMove = cell + 1;
        } else {
            this.turn = this.turn.other();
        }
    }

    /**
     * @return the board if this cell was
     * to be played
     */
    public Board fakePlay (int cell) {
        var copy = clone();
        copy.playCell(cell);
        return copy;
    }

    /**
     *
     * @return a random empty cell
     */
    public int getRandomEmptyCell() {
        checkGameNotOver();

        if (this.turnsCount < 5) {
            while (true) {
                int move = RANDOM.nextInt(9) + 1;
                if (this.cells[move - 1] == null) {
                    return move;
                }
            }
        } else {
            List<Integer> emptyCells = new ArrayList<>(5); // Should be 4 I guess, but brain to fried to think
            for (int i = 0; i < this.cells.length; i++) {
                if (this.cells[i] == null) {
                    emptyCells.add(i);
                }
            }
            return emptyCells.get(RANDOM.nextInt(emptyCells.size())) + 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder game = new StringBuilder();

        for (int i = 0; i < 9; i += 3) {
            game.append(" ").
                    append(cellLook(i)).
                    append(" ║ ").
                    append(cellLook(i + 1)).
                    append(" ║ ").
                    append(cellLook(i + 2)).
                    append(" \n");
            if (i < 6) {
                game.append("═══╬═══╬═══\n");
            }
        }
        // TODO change to env.new_line

        return game.toString();
    }

    /**
     * @return the look of the cell at the given index
     */
    private String cellLook(int index) {
        XO cell = this.cells[index];
        return cell == null ? " " : cell.toString();
    }

    @Override
    public Board clone() {
        try {
            Board clone = (Board) super.clone();
            clone.cells = this.cells.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
