package St3.FinalExam.Ex2;

public class Main {
    public static void main(String[] args) {
        // Test constructor and getters
        Sparse2DArray<String> sparse2DArray = new Sparse2DArray<>(3, 3);
        System.out.println("Size X: " + sparse2DArray.getSizeX());
        System.out.println("Size Y: " + sparse2DArray.getSizeY());

        // Test set and get methods
        sparse2DArray.set(0, 0, "a");
        sparse2DArray.set(1, 1, "b");
        sparse2DArray.set(2, 2, "c");
        System.out.println("Value at (0, 0): " + sparse2DArray.get(0, 0));
        System.out.println("Value at (1, 1): " + sparse2DArray.get(1, 1));
        System.out.println("Value at (2, 2): " + sparse2DArray.get(2, 2));
        System.out.println("Value at (0, 1): " + sparse2DArray.get(0, 1));

        // Test hasDuplicateValues method
        System.out.println("Has duplicate values: " + sparse2DArray.hasDuplicateValues());
        sparse2DArray.set(1, 2, "a");
        System.out.println("Has duplicate values: " + sparse2DArray.hasDuplicateValues());

        // Test fromArray method
        String[][] array2d = {
                {"a", null, null},
                {null, "b", null},
                {null, null, "c"}
        };
        Sparse2DArray<String> fromArraySparse2DArray = Sparse2DArray.fromArray(array2d);
        System.out.println("Value at (0, 0): " + fromArraySparse2DArray.get(0, 0));
        System.out.println("Value at (1, 1): " + fromArraySparse2DArray.get(1, 1));
        System.out.println("Value at (2, 2): " + fromArraySparse2DArray.get(2, 2));
        System.out.println("Value at (0, 1): " + fromArraySparse2DArray.get(0, 1));

        // Test toArray method
        String[][] toArray2d = fromArraySparse2DArray.toArray(String[]::new, String[][]::new);
        System.out.println("Array2D:");
        for (String[] row : toArray2d) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
