package GPT.St4.HW7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PasswordIterator implements Iterator<String> {
    private final int length;
    private long currentIndex;
    private final long maxIndex;

    public PasswordIterator(int length) {
        if (length < 1 || length > 9) {
            Util.badArgument("Password length must be between 1 and 9");
        }
        this.length = length;
        this.currentIndex = 0;
        this.maxIndex = (long) Math.pow(10, length);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < maxIndex;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords available");
        }
        String password = Util.longToStringWithLength(currentIndex, length);
        currentIndex++;
        return password;
    }
}
