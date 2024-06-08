package St1.HW7;

import java.util.function.Function;

public class PasswordBreaker {
    public static String findPassword(Function<String, Integer> hashFunction,
                                      int passwordLength, String salt, int saltedPasswordHashValue) {

        if (salt == null || hashFunction == null) {
            Util.badArgument("Invalid arguments");
        }

        PasswordIterator iterator = new PasswordIterator(passwordLength);
        while (iterator.hasNext()) {
            String password = iterator.next();
            String saltedPassword = password + salt;
            if (hashFunction.apply(saltedPassword).equals(saltedPasswordHashValue)) {
                return password;
            }
        }
        return null; // No password found
    }
}
