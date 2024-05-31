package GPT.St4.HW7;

import java.util.function.Function;

public class PasswordBreaker {
    public static String findPassword(Function<String, Integer> hashFunction, int passwordLength, String salt, int saltedPasswordHashValue) {
        if (hashFunction == null || salt == null) {
            Util.badArgument("Hash function and salt must not be null");
        }

        PasswordIterator iterator = new PasswordIterator(passwordLength);

        while (iterator.hasNext()) {
            String candidate = iterator.next();
            String combined = candidate + salt;
            if (hashFunction.apply(combined) == saltedPasswordHashValue) {
                return candidate;
            }
        }

        return null;
    }
}
