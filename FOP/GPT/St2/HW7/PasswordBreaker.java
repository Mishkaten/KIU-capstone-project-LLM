package GPT.St2.HW7;

import java.util.function.Function;

public class PasswordBreaker {

    public static String findPassword(Function<String, Integer> hashFunction, int passwordLength, String salt, int saltedPasswordHashValue) {
        if (hashFunction == null || salt == null) {
            Util.badArgument("Hash function and salt cannot be null.");
        }

        PasswordIterator iterator = new PasswordIterator(passwordLength);
        while (iterator.hasNext()) {
            String password = iterator.next();
            String saltedPassword = password + salt;
            if (hashFunction.apply(saltedPassword) == saltedPasswordHashValue) {
                return password;
            }
        }

        return null;
    }
}
