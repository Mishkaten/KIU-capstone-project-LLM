public class Util {
    public static void badArgument(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }

    public static void noSuchElement(String errorMessage) {
        throw new NoSuchElementException(errorMessage);
    }

    public static String longToStringWithLength(long value, int length) {
        String format = "%0" + length + "d";
        return String.format(format, value);
    }
}
