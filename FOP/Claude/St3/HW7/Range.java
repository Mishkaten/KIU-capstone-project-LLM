package Claude.St3.HW7;

import java.util.Iterator;

class Range implements Iterable<Integer> {
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

    public Iterator<Integer> iterator() {
        return new RangeIterator(begin, end, stride);
    }

    private static class RangeIterator implements Iterator<Integer> {
        private int current;
        private final int end;
        private final int stride;

        public RangeIterator(int begin, int end, int stride) {
            this.current = begin;
            this.end = end;
            this.stride = stride;
        }

        public boolean hasNext() {
            return (stride > 0 && current <= end) || (stride < 0 && current >= end);
        }

        public Integer next() {
            if (!hasNext()) {
                Util.noSuchElement("No more elements in the range");
            }
            int value = current;
            current += stride;
            return value;
        }
    }
}