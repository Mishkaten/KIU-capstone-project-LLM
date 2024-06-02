package St1.FinalExam.Ex2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Sparse2DArray<T> {
    private final int sizeX;
    private final int sizeY;
    private List<Element<T>> elements;

    public Sparse2DArray(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.elements = new ArrayList<>();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void set(int x, int y, T value) {
        checkBounds(x, y);
        Element<T> element = findElement(x, y);
        if (value == null) {
            if (element != null) {
                elements.remove(element);
            }
        } else {
            if (element != null) {
                element.value = value;
            } else {
                elements.add(new Element<>(x, y, value));
            }
        }
    }

    public T get(int x, int y) {
        checkBounds(x, y);
        Element<T> element = findElement(x, y);
        return element != null ? element.value : null;
    }

    public boolean hasDuplicateValues() {
        Set<T> uniqueValues = new HashSet<>();
        for (Element<T> element : elements) {
            if (!uniqueValues.add(element.value)) {
                return true;
            }
        }
        return false;
    }

    public static <T> Sparse2DArray<T> fromArray(T[][] array2d) {
        if (array2d == null) {
            throw new RuntimeException("Invalid array: array2d is null");
        }
        int sizeX = array2d.length;
        int sizeY = 0;
        for (T[] row : array2d) {
            if (row == null) {
                throw new RuntimeException("Invalid array: row is null");
            }
            if (sizeY == 0) {
                sizeY = row.length;
            } else if (row.length != sizeY) {
                throw new RuntimeException("Invalid array: inconsistent row length");
            }
        }
        Sparse2DArray<T> sparse2DArray = new Sparse2DArray<>(sizeX, sizeY);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                T value = array2d[x][y];
                if (value != null) {
                    sparse2DArray.set(x, y, value);
                }
            }
        }
        return sparse2DArray;
    }

    public T[][] toArray(Function<Integer, T[]> array1dFunc, Function<Integer, T[][]> array2dFunc) {
        T[][] array2d = array2dFunc.apply(sizeX);
        for (int x = 0; x < sizeX; x++) {
            array2d[x] = array1dFunc.apply(sizeY);
        }
        for (Element<T> element : elements) {
            array2d[element.x][element.y] = element.value;
        }
        return array2d;
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            throw new IndexOutOfBoundsException("Invalid index: (" + x + ", " + y + ")");
        }
    }

    private Element<T> findElement(int x, int y) {
        for (Element<T> element : elements) {
            if (element.x == x && element.y == y) {
                return element;
            }
        }
        return null;
    }

    private static class Element<T> {
        private int x;
        private int y;
        private T value;

        public Element(int x, int y, T value) {
            this.x = x;
            this.y = y;
            if (value == null) {
                throw new RuntimeException("Value cannot be null");
            }
            this.value = value;
        }
    }
}