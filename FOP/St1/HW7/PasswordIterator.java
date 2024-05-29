// Reading the content of the file PasswordIterator.java
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PasswordIterator implements Iterator<String> {
    private List<String> passwords;
    private int currentIndex;

    public PasswordIterator(List<String> passwords) {
        this.passwords = passwords;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < passwords.size();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return passwords.get(currentIndex++);
    }
}
