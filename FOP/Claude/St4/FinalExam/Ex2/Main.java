package St4.FinalExam.Ex2;

public class Main {
    public static void main(String[] args) {
        // Create a sparse 2D array
        Sparse2DArray<String> sparse2DArray = new Sparse2DArray<>(3, 3);
        sparse2DArray.set(0, 0, "a");
        sparse2DArray.set(1, 1, "b");
        sparse2DArray.set(2, 2, "c");

        // Test get and set methods
        System.out.println(sparse2DArray.get(0, 0)); // Output: a
        System.out.println(sparse2DArray.get(1, 1)); // Output: b
        System.out.println(sparse2DArray.get(2, 2)); // Output: c
        System.out.println(sparse2DArray.get(0, 1)); // Output: null

        // Test hasDuplicateValues method
        System.out.println(sparse2DArray.hasDuplicateValues()); // Output: false
        sparse2DArray.set(1, 1, "a");
        System.out.println(sparse2DArray.hasDuplicateValues()); // Output: true

        // Test fromArray method
        String[][] array2d = {
                {"a", null, null},
                {null, "b", null},
                {null, null, "c"}
        };
        Sparse2DArray<String> fromArray = Sparse2DArray.fromArray(array2d);
        System.out.println(fromArray.get(0, 0)); // Output: a
        System.out.println(fromArray.get(1, 1)); // Output: b
        System.out.println(fromArray.get(2, 2)); // Output: c

        // Test toArray method
        String[][] toArray = sparse2DArray.toArray(String[]::new, String[][]::new);
        for (String[] row : toArray) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        // Output:
        // a null null
        // null a null
        // null null c
    }
}