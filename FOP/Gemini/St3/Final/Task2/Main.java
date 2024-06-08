package pgdp.sparsearray;

public class Main {
    public static void main(String[] args) {
        // Create a Sparse2DArray of size 5x5
        Sparse2DArray<String> sparseArray = new Sparse2DArray<>(5, 5);

        // Set some values
        sparseArray.set(1, 1, "A");
        sparseArray.set(2, 3, "B");
        sparseArray.set(4, 4, "C");

        // Print out the array dimensions
        System.out.println("Array dimensions: " + sparseArray.getSizeX() + "x" + sparseArray.getSizeY());
        // Expected output: Array dimensions: 5x5

        // Get and print values
        System.out.println("Value at (1,1): " + sparseArray.get(1, 1));
        // Expected output: Value at (1,1): A

        System.out.println("Value at (2,3): " + sparseArray.get(2, 3));
        // Expected output: Value at (2,3): B

        System.out.println("Value at (4,4): " + sparseArray.get(4, 4));
        // Expected output: Value at (4,4): C

        System.out.println("Value at (0,0): " + sparseArray.get(0, 0));
        // Expected output: Value at (0,0): null

        // Check for duplicate values
        System.out.println("Has duplicate values: " + sparseArray.hasDuplicateValues());
        // Expected output: Has duplicate values: false

        // Add a duplicate value
        sparseArray.set(3, 2, "A");
        System.out.println("Has duplicate values after adding a duplicate: " + sparseArray.hasDuplicateValues());
        // Expected output: Has duplicate values after adding a duplicate: true

        // Remove a value by setting it to null
        sparseArray.set(1, 1, null);
        System.out.println("Value at (1,1) after removal: " + sparseArray.get(1, 1));
        // Expected output: Value at (1,1) after removal: null

        // Convert Sparse2DArray to regular 2D array
        String[][] regularArray = sparseArray.toArray(
                size -> new String[size],
                size -> new String[size][]
        );

        System.out.println("Regular 2D Array:");
        for (int i = 0; i < regularArray.length; i++) {
            for (int j = 0; j < regularArray[i].length; j++) {
                System.out.print(regularArray[i][j] + " ");
            }
            System.out.println();
        }
        // Expected output (nulls replaced with - for clarity):
        // - - - - -
        // - - A - -
        // - - - - -
        // - - - B -
        // - - - - -
    }
}
