package St4.FinalExam.Ex2;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Sparse2DArray<T> {
    private final int sizeX;
    private final int sizeY;
    private final List<Element<T>> elements;

    public Sparse2DArray(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.elements = new LinkedList<>();
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public T get(int x, int y) {
        checkBounds(x, y);
        for (Element<T> element : elements) {
            if (element.x == x && element.y == y) {
                return element.value;
            }
        }
        return null;
    }

    public void set(int x, int y, T value) {
        checkBounds(x, y);
        if (value == null) {
            elements.removeIf(element -> element.x == x && element.y == y);
        } else {
            for (Element<T> element : elements) {
                if (element.x == x && element.y == y) {
                    element.value = value;
                    return;
                }
            }
            elements.add(new Element<>(x, y, value));
        }
    }

    public boolean hasDuplicateValues() {
        return elements.stream()
                .map(element -> element.value)
                .distinct()
                .count() < elements.size();
    }

    public static <T> Sparse2DArray<T> fromArray(T[][] array2d) {
        if (array2d == null) {
            throw new RuntimeException("Invalid array: null");
        }
        int sizeX = array2d.length;
        int sizeY = array2d[0].length;
        for (T[] array1d : array2d) {
            if (array1d == null) {
                throw new RuntimeException("Invalid array: null row");
            }
            if (array1d.length != sizeY) {
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

    private static class Element<T> {
        private final int x;
        private final int y;
        private T value;

        public Element(int x, int y, T value) {
            this.x = x;
            this.y = y;
            setValue(value);
        }

        public void setValue(T value) {
            if (value == null) {
                throw new RuntimeException("Value cannot be null");
            }
            this.value = value;
        }
    }
}