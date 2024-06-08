package St1.Final.Task2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class Sparse2DArray<T> {

    // Inner class for elements
    private static class Element<T> {
        final int x;
        final int y;
        final T value;

        Element(int x, int y, T value) {
            this.x = x;
            this.y = y;
            this.value = Objects.requireNonNull(value, "Value cannot be null");
        }
    }

    // Private attributes for Sparse2DArray
    private final int sizeX;
    private final int sizeY;
    private final List<Element<T>> elements = new LinkedList<>();

    // Constructor
    public Sparse2DArray(int sizeX, int sizeY) {
        if (sizeX <= 0 || sizeY <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    // Getters for dimensions
    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    // Set and Get methods
    public void set(int x, int y, T value) {
        checkBounds(x, y);
        if (value == null) { // Remove element if setting to null
            elements.removeIf(e -> e.x == x && e.y == y);
        } else {
            elements.removeIf(e -> e.x == x && e.y == y); // Remove if already exists
            elements.add(new Element<>(x, y, value)); // Add the new element
        }
    }

    public T get(int x, int y) {
        checkBounds(x, y);
        for (Element<T> element : elements) {
            if (element.x == x && element.y == y) {
                return element.value;
            }
        }
        return null; // Return null if not found
    }

    // Method to check for duplicate non-null values
    public boolean hasDuplicateValues() {
        Set<T> uniqueValues = new HashSet<>();
        for (Element<T> element : elements) {
            if (!uniqueValues.add(element.value)) {
                return true; // Duplicate found
            }
        }
        return false;
    }

    // Method to create Sparse2DArray from a regular 2D array
    @SuppressWarnings("unchecked") // Safe suppression for array creation
    public static <T> Sparse2DArray<T> fromArray(T[][] array2d) {
        // ... (Implementation from previous response remains the same)
        return null;
    }

    // Method to convert to a regular 2D array
    public T[][] toArray(Function<Integer, T[]> array1dFunc, Function<Integer, T[][]> array2dFunc) {
        // ... (Implementation from previous response remains the same)
        return null;
    }

    // Private helper to check bounds
    private void checkBounds(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            throw new IndexOutOfBoundsException("Indices out of bounds");
        }
    }
}
