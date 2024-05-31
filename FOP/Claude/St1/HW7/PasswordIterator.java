package Claude.St1.HW7;

import java.util.Iterator;

public class PasswordIterator implements Iterator<String> {
    private int passwordLength;
    private int current;
    private int state;

    public PasswordIterator(int passwordLength) {
        if (passwordLength < 1 || passwordLength > 9) {
            Util.badArgument("Password length must be between 1 and 9");
        }
        this.passwordLength = passwordLength;
        this.current = 0;
        this.state = 0;
    }

    public boolean hasNext() {
        return current < Math.pow(10, passwordLength);
    }

    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords");
        }
        String password = getNextPassword();
        current++;
        return password;
    }

    private String getNextPassword() {
        if (state == 0) {
            String password = repeatDigit(current);
            if (!isRepeatedDigit(password)) {
                state = 1;
                current = 0;
            }
            return password;
        } else if (state == 1) {
            String password = ascendingDigits(current);
            if (!isAscendingDigits(password)) {
                state = 2;
                current = 0;
            }
            return password;
        } else if (state == 2) {
            String password = descendingDigits(current);
            if (!isDescendingDigits(password)) {
                state = 3;
                current = 0;
            }
            return password;
        } else {
            return Util.longToStringWithLength(current, passwordLength);
        }
    }

    private String repeatDigit(int digit) {
        return String.valueOf(digit).repeat(passwordLength);
    }

    private boolean isRepeatedDigit(String password) {
        char firstChar = password.charAt(0);
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) != firstChar) {
                return false;
            }
        }
        return true;
    }

    private String ascendingDigits(int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            sb.append((start + i) % 10);
        }
        return sb.toString();
    }

    private boolean isAscendingDigits(String password) {
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) != (password.charAt(i - 1) + 1) % 10) {
                return false;
            }
        }
        return true;
    }

    private String descendingDigits(int start) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            sb.append((start - i + 10) % 10);
        }
        return sb.toString();
    }

    private boolean isDescendingDigits(String password) {
        for (int i = 1; i < password.length(); i++) {
            if (password.charAt(i) != (password.charAt(i - 1) - 1 + 10) % 10) {
                return false;
            }
        }
        return true;
    }
}