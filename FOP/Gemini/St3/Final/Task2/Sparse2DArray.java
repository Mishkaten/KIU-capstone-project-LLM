package pgdp.sparsearray;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Sparse2DArray<T> {
    private final int sizeX;
    private final int sizeY;
    private final List<Element<T>> elements;
    private final Map<T, Integer> valueCounts;

    // Inner class for representing elements
    private static class Element<T> {
        final int x;
        final int y;
        T value;

        Element(int x, int y, T value) {
            if (value == null) {
                throw new RuntimeException("Value cannot be null.");
            }
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public Sparse2DArray(int sizeX, int sizeY) {
        if (sizeX <= 0 || sizeY <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive.");
        }
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.elements = new LinkedList<>();
        this.valueCounts = new HashMap<>(); // To track duplicates
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public T get(int x, int y) {
        validateIndices(x, y);
        for (Element<T> element : elements) {
            if (element.x == x && element.y == y) {
                return element.value;
            }
        }
        return null; // Return null for empty cells
    }

    public void set(int x, int y, T value) {
        validateIndices(x, y);

        if (value == null) {
            remove(x, y);  // Remove the element if value is null
        } else {
            for (Element<T> element : elements) {
                if (element.x == x && element.y == y) {
                    element.value = value;  // Update existing element
                    updateValueCounts(value);
                    return;
                }
            }
            elements.add(new Element<>(x, y, value)); // Add new element
            updateValueCounts(value);
        }
    }

    private void updateValueCounts(T value) {
        valueCounts.put(value, valueCounts.getOrDefault(value, 0) + 1);
    }

    // Helper method to remove an element
    private void remove(int x, int y) {
        elements.removeIf(e -> e.x == x && e.y == y);
    }

    // Check for duplicate values (not null)
    public boolean hasDuplicateValues() {
        for (int count : valueCounts.values()) {
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    // Create a Sparse2DArray from a regular 2D array
    public static <T> Sparse2DArray<T> fromArray(T[][] array2d) {
        if (array2d == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int sizeX = array2d.length;
        if (sizeX == 0) {
            throw new IllegalArgumentException("Input array cannot be empty.");
        }

        int sizeY = array2d[0].length; // Assume all rows have the same length
        for (T[] row : array2d) {
            if (row == null || row.length != sizeY) {
                throw new IllegalArgumentException("Inconsistent row lengths.");
            }
        }

        Sparse2DArray<T> sparseArray = new Sparse2DArray<>(sizeX, sizeY);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (array2d[x][y] != null) {
                    sparseArray.set(x, y, array2d[x][y]);
                }
            }
        }
        return sparseArray;
    }

    // Convert back to a regular 2D array
    public T[][] toArray(Function<Integer, T[]> array1dFunc, Function<Integer, T[][]> array2dFunc) {
        T[][] array = array2dFunc.apply(sizeX);
        for (int i = 0; i < sizeX; i++) {
            array[i] = array1dFunc.apply(sizeY);
        }

        for (Element<T> element : elements) {
            array[element.x][element.y] = element.value;
        }
        return array;
    }

    // Validate x and y indices
    private void validateIndices(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            throw new IndexOutOfBoundsException("Invalid indices: (" + x + ", " + y + ")");
        }
    }
}
