package St1.HW7;

import java.util.Iterator;

public class PasswordIterator implements Iterator<String> {
    private final int passwordLength;
    private long currentCode = 0;
    private int state = 0; // 0: single digit, 1: ascending, 2: descending, 3: others

    public PasswordIterator(int passwordLength) {
        if (passwordLength < 1 || passwordLength > 9) {
            Util.badArgument("Password length must be between 1 and 9");
        }
        this.passwordLength = passwordLength;
    }

    @Override
    public boolean hasNext() {
        return currentCode < Math.pow(10, passwordLength);
    }

    @Override
    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords");
        }
        String password = generatePassword();
        incrementCurrentCode();
        return password;
    }

    private String generatePassword() {
        switch (state) {
            case 0:
                return Util.longToStringWithLength(currentCode % 10, passwordLength);
            case 1:
                return Util.longToStringWithLength(currentCode, passwordLength);
            case 2:
                return Util.longToStringWithLength((long) (Math.pow(10, passwordLength) - 1 - currentCode), passwordLength);
            default: // case 3
                return Util.longToStringWithLength(currentCode + calculateOffset(), passwordLength);
        }
    }

    private void incrementCurrentCode() {
        currentCode++;
        if (state == 0 && currentCode % 10 == 0) {
            state++;
        } else if (state == 1 && currentCode % 10 == 9) {
            state++;
        } else if (state == 2 && currentCode % 10 == 0) {
            state++;
            currentCode = 0; // Reset for "others"
        }
    }

    // Calculate offset for "others" to skip ascending and descending sequences
    private long calculateOffset() {
        return 10 * (passwordLength - 1) + 18;
    }
}
