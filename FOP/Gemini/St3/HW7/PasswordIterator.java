package St1.HW7;

import java.util.Iterator;

class PasswordIterator implements Iterator<String> {
    private int passwordLength;
    private long currentCode;
    private int state;

    public PasswordIterator(int passwordLength) {
        if (passwordLength < 1 || passwordLength > 9) {
            Util.badArgument("Password length must be between 1 and 9");
        }
        this.passwordLength = passwordLength;
        this.currentCode = 0;
        this.state = 0;
    }

    public boolean hasNext() {
        return currentCode < Math.pow(10, passwordLength);
    }

    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords");
        }
        String password = generatePassword();
        currentCode++;
        return password;
    }

    private String generatePassword() {
        switch (state) {
            case 0:
                // Codes consisting of the repetition of a single digit
                if (currentCode < 10) {
                    return Util.longToStringWithLength(currentCode, passwordLength);
                }
                state++;
                currentCode = 0;
            case 1:
                // Codes consisting of ascending sequences of digits
                if (currentCode < Math.pow(10, passwordLength - 1)) {
                    StringBuilder sb = new StringBuilder();
                    long code = currentCode;
                    for (int i = 0; i < passwordLength; i++) {
                        sb.append((int) (code % 10));
                        code /= 10;
                    }
                    return sb.reverse().toString();
                }
                state++;
                currentCode = 0;
            case 2:
                // Codes consisting of decreasing sequences of digits
                if (currentCode < Math.pow(10, passwordLength - 1)) {
                    StringBuilder sb = new StringBuilder();
                    long code = currentCode;
                    for (int i = 0; i < passwordLength; i++) {
                        sb.append(9 - (int) (code % 10));
                        code /= 10;
                    }
                    return sb.reverse().toString();
                }
                state++;
                currentCode = 0;
            default:
                // All remaining codes of the given length
                return Util.longToStringWithLength(currentCode, passwordLength);
        }
    }
}