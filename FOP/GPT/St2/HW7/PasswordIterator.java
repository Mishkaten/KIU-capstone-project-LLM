package GPT.St2.HW7;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.Set;

public class PasswordIterator implements Iterator<String> {
    private final int length;
    private final Set<String> seen;
    private long current;

    public PasswordIterator(int length) {
        if (length < 1 || length > 9) {
            Util.badArgument("Password length must be between 1 and 9.");
        }
        this.length = length;
        this.seen = new HashSet<>();
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < Math.pow(10, length);
    }

    @Override
    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords available.");
        }

        String result = null;
        while (hasNext() && (result == null || seen.contains(result))) {
            result = generateNextPassword();
            current++;
        }

        if (result != null) {
            seen.add(result);
            return result;
        } else {
            Util.noSuchElement("No more passwords available.");
            return null; // Unreachable, but required by the compiler
        }
    }

    private String generateNextPassword() {
        return Util.longToStringWithLength(current, length);
    }
}
