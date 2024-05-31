package Claude.St4.HW7;

import java.util.Iterator;

public class PasswordIterator implements Iterator<String> {
    private int passwordLength;
    private long currentValue;
    private long maxValue;

    public PasswordIterator(int passwordLength) {
        if (passwordLength < 1 || passwordLength > 9) {
            Util.badArgument("Invalid password length. It should be between 1 and 9.");
        }
        this.passwordLength = passwordLength;
        this.currentValue = 0;
        this.maxValue = (long) Math.pow(10, passwordLength);
    }

    public boolean hasNext() {
        return currentValue < maxValue;
    }

    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords available.");
        }

        String password = Util.longToStringWithLength(currentValue, passwordLength);
        currentValue++;

        // Check for repetitive digits
        if (isRepetitive(password)) {
            return password;
        }

        // Check for ascending digits
        if (isAscending(password)) {
            return password;
        }

        // Check for descending digits
        if (isDescending(password)) {
            return password;
        }

        // Return the remaining password
        return password;
    }

    private boolean isRepetitive(String password) {
        char firstDigit = password.charAt(0);
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) != firstDigit) {
                return false;
            }
        }
        return true;
    }

    private boolean isAscending(String password) {
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) - password.charAt(i - 1) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isDescending(String password) {
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i - 1) - password.charAt(i) != 1) {
                return false;
            }
        }
        return true;
    }
}