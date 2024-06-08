package St4.Final.Task2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Sparse2DArray<T> {

    private final int sizeX;
    private final int sizeY;
    private final Map<Integer, Map<Integer, T>> elements;

    public Sparse2DArray(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.elements = new HashMap<>();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            throw new IndexOutOfBoundsException("Invalid coordinates: (" + x + ", " + y + ")");
        }
    }

    public T get(int x, int y) {
        checkBounds(x, y);
        return elements.getOrDefault(x, new HashMap<>()).get(y);
    }

    public void set(int x, int y, T value) {
        checkBounds(x, y);
        if (value == null) {
            Map<Integer, T> rowMap = getRowMap(x);
            rowMap.remove(y);
            if (rowMap.isEmpty()) {
                elements.remove(x); // Remove empty row
            }
        } else {
            getRowMap(x).put(y, value);
        }
    }

    public boolean hasDuplicateValues() {
        return elements.values().stream()
                .flatMap(row -> row.values().stream())
                .distinct()
                .count() < elements.values().stream().mapToInt(Map::size).sum();
    }

    public static <T> Sparse2DArray<T> fromArray(T[][] array2d) {
        if (array2d == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }

        int sizeX = array2d.length;
        if (sizeX == 0) {
            throw new IllegalArgumentException("Input array cannot be empty");
        }
        int sizeY = array2d[0].length;
        for (T[] row : array2d) {
            if (row == null || row.length != sizeY) {
                throw new IllegalArgumentException("Invalid input array dimensions");
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

    public T[][] toArray(Function<Integer, T[]> array1dFunc, Function<Integer, T[][]> array2dFunc) {
        T[][] result = array2dFunc.apply(sizeX);

        for (int i = 0; i < sizeX; i++) {
            result[i] = array1dFunc.apply(sizeY); // Initialize each row with nulls

            // If a row exists in the sparse array, fill in the values
            if (elements.containsKey(i)) {
                Map<Integer, T> rowMap = elements.get(i);
                for (int j : rowMap.keySet()) { // Use keySet() to iterate over columns
                    result[i][j] = rowMap.get(j);
                }
            }
        }
        return result;
    }
    private Map<Integer, T> getRowMap(int x) {
        if (!elements.containsKey(x)) {
            elements.put(x, new HashMap<>());
        }
        return elements.get(x);
    }


}
