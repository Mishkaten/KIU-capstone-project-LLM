package GPT.St2.HW7;

import java.util.NoSuchElementException;

public class Util {

    public static void badArgument(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }

    public static void noSuchElement(String errorMessage) {
        throw new NoSuchElementException(errorMessage);
    }

    public static String longToStringWithLength(long value, int length) {
        return String.format("%0" + length + "d", value);
    }
}
