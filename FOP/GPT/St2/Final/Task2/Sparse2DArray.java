package pgdp.sparsearray;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Sparse2DArray<T> {

    final int x;
    final int y;
    int value;
    T[][] element;

    public Sparse2DArray(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;

        if (value == 0)
            throw new RuntimeException("Value is null! ");
        else {
            List<T> elements = new LinkedList<T>();

        }

    }

    public void set(int x, int y, int value) {

    }

    public T get(int x, int y) {
        for (int i = 0; i < element.length; i++) {
            for (int j = 0; j < element.length; j++) {
                element[x][y] = element[i][j];
            }
        }
        return element[x][y];
    }

    public boolean hasDuplicateValues() {

        return false;

    }

    public static <T> Sparse2DArray<T> fromArray(T[][] array2d) {

        return null;

    }

    public T[][] toArray(Function<Integer, T[]> array1dFunc, Function<Integer, T[][]> array2dFunc) {
        return null;

    }
}
