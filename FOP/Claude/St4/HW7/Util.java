package Claude.St4.HW7;

import java.util.NoSuchElementException;

/**
 * Do not change this class!
 */
public class Util {

    public static void noSuchElement(String errorMessage) {
        throw new NoSuchElementException(errorMessage);
    }

    public static void badArgument(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }

    /**
     * Provides a non-negative {@code value} as String (at least) of length
     * {@code length}. If the decimal representation of {@code value} has less than {@code length}
     * digits, it is filled up with leading zeros so that the resulting string has length
     * {@code length}. If the decimal representation of {@code value} has already length {@code length}
     * or more, this is returned.
     *
     * @param value  the value to be represented
     * @param length The length of the representation
     * @return Possibly with leading zeros extended String representation of
     *         {@code value}
     */
    public static String longToStringWithLength(long value, int length) {
        return String.format("%0" + length + "d", value);
    }

}
