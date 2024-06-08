package St4.HW7;


import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // Define a simple hash function for testing
        Function<String, Integer> hashFunction = s -> s.hashCode() % 1000;

        // Test PasswordIterator
        PasswordIterator iterator = new PasswordIterator(3); // Generate 3-digit passwords
        System.out.println("Generated passwords:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        // Expected output: 000, 001, 002, ..., 998, 999

        // Test findPassword in PasswordBreaker
        String salt = "salt";
        int saltedPasswordHashValue = hashFunction.apply("123salt");
        String foundPassword = PasswordBreaker.findPassword(hashFunction, 3, salt, saltedPasswordHashValue);
        System.out.println("Found password: " + foundPassword);
        // Expected output: 123

        // Test Range
        Range range = new Range(1, 10);
        System.out.println("Range from 1 to 10:");
        for (int value : range) {
            System.out.print(value + " ");
        }
        System.out.println();
        // Expected output: 1 2 3 4 5 6 7 8 9 10
    }
}
