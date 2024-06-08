package St4.HW7;

import java.util.Iterator;

public class PasswordIterator implements Iterator<String> {
    private final int passwordLength;
    private int currentType = 0; // 0: single digit, 1: ascending, 2: descending, 3: others
    private long currentValue = 0;

    public PasswordIterator(int passwordLength) {
        if (passwordLength < 1 || passwordLength > 9) {
            Util.badArgument("Password length must be between 1 and 9");
        }
        this.passwordLength = passwordLength;
    }

    @Override
    public boolean hasNext() {
        return currentType < 4;  // 4 types of passwords
    }

    @Override
    public String next() {
        if (!hasNext()) {
            Util.noSuchElement("No more passwords");
        }

        String password;
        switch (currentType) {
            case 0:  // Single digit
                password = Util.longToStringWithLength(currentValue, passwordLength);
                currentValue++;
                if (currentValue >= 10) currentType++;  // Move to next type
                break;
            case 1:  // Ascending
                password = Util.longToStringWithLength(currentValue, passwordLength);
                currentValue += 111111111L / (long) Math.pow(10, 9 - passwordLength);
                if (password.charAt(0) == '9') currentType++;
                break;
            case 2:  // Descending
                password = Util.longToStringWithLength(currentValue, passwordLength);
                currentValue -= 111111111L / (long) Math.pow(10, 9 - passwordLength);
                if (password.charAt(0) == '0') currentType++;
                break;
            case 3:  // Others
                password = Util.longToStringWithLength(currentValue, passwordLength);
                currentValue++;
                if (currentValue >= Math.pow(10, passwordLength)) currentType++;
                break;
            default:
                throw new IllegalStateException("Unexpected currentType");
        }
        return password;
    }
}
