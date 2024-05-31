package GPT.St3.HW7;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class PasswordIterator implements Iterator<String> {
    private final int length;
    private final TreeSet<String> passwords;
    private Iterator<String> iterator;

    public PasswordIterator(int length) {
        if (length < 1 || length > 9) {
            Util.badArgument("Password length must be between 1 and 9");
        }
        this.length = length;
        this.passwords = new TreeSet<>();
        generatePasswords();
        this.iterator = passwords.iterator();
    }

    private void generatePasswords() {
        // Repetitive digits
        for (int i = 0; i < 10; i++) {
            passwords.add(String.valueOf(i).repeat(length));
        }

        // Ascending sequences
        for (int i = 0; i <= 10 - length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                sb.append(i + j);
            }
            passwords.add(sb.toString());
        }

        // Descending sequences
        for (int i = 9; i >= length - 1; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < length; j++) {
                sb.append(i - j);
            }
            passwords.add(sb.toString());
        }

        // All remaining codes
        long max = (long) Math.pow(10, length);
        for (long i = 0; i < max; i++) {
            passwords.add(Util.longToStringWithLength(i, length));
        }
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords");
        }
        return iterator.next();
    }
}
