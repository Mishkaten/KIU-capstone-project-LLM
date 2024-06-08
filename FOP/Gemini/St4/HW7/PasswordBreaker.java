package St4.HW7;

import java.util.function.Function;

class PasswordBreaker {
    public static String findPassword(Function<String, Integer> hashFunction, int passwordLength,
                                      String salt, int saltedPasswordHashValue) {
        if (salt == null || hashFunction == null) {
            Util.badArgument("Salt and hash function cannot be null");
        }
        PasswordIterator iterator = new PasswordIterator(passwordLength);
        while (iterator.hasNext()) {
            String password = iterator.next();
            String saltedPassword = password + salt;
            int hashValue = hashFunction.apply(saltedPassword);
            if (hashValue == saltedPasswordHashValue) {
                return password;
            }
        }
        return null;
    }
}