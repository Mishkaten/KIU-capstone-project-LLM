public class Util {
    public static void badArgument(String errorMessage) {
        throw new IllegalArgumentException(errorMessage);
    }

    public static void noSuchElement(String errorMessage) {
        throw new NoSuchElementException(errorMessage);
    }

    public static String longToStringWithLength(long value, int length) {
        String str = Long.toString(value);
        while (str.length() < length) {
            str = "0" + str;
        }
        return str;
    }
}
