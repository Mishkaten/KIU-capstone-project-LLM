package fop.w8iter;
// Reading the content of the file PasswordBreaker.java
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PasswordBreaker {
    private PasswordIterator iterator;

    public PasswordBreaker(String filePath) {
        List<String> passwords = Util.readPasswordsFromFile(filePath);
        iterator = new PasswordIterator(passwords);
    }

    public String breakPassword(String correctPassword) {
        while (iterator.hasNext()) {
            String attempt = iterator.next();
            if (attempt.equals(correctPassword)) {
                return attempt;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PasswordBreaker <passwordFilePath> <correctPassword>");
            return;
        }

        String passwordFilePath = args[0];
        String correctPassword = args[1];

        PasswordBreaker breaker = new PasswordBreaker(passwordFilePath);
        String brokenPassword = breaker.breakPassword(correctPassword);

        if (brokenPassword != null) {
            System.out.println("Password found: " + brokenPassword);
        } else {
            System.out.println("Password not found.");
        }
    }
}
