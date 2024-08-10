package tictactoe.game;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Enum representing X and O
 */
public enum XO {
    X, O;

    private static final Random RANDOM = new Random();

    public static final List<XO> VALUES = Arrays.stream(XO.values()).toList();

    /**
     * @return a random XO value
     */
    public static XO randomOne() {
        return VALUES.get(RANDOM.nextInt(VALUES.size()));
    }

    /**
     * @return the opposite XO value
     */
    public XO other() {
        return this == X ? O : X;
    }

    /**
     * @return  the string representation of the XO value
     */
    @Override
    public String toString() {
        return this == X ? "X" : "O";
    }
}
