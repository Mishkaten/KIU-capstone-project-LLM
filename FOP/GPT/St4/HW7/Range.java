package GPT.St4.HW7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private final int begin;
    private final int end;
    private final int stride;

    public Range(int begin, int end, int stride) {
        if (stride <= 0) {
            Util.badArgument("Stride must be greater than 0");
        }
        this.begin = begin;
        this.end = end;
        this.stride = stride;
    }

    public Range(int begin, int end) {
        this(begin, end, 1);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int current = begin;

        @Override
        public boolean hasNext() {
            if (begin < end) {
                return current <= end;
            } else {
                return current >= end;
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                Util.noSuchElement("No more elements in range");
            }
            int value = current;
            current += (begin < end ? stride : -stride);
            return value;
        }
    }
}
