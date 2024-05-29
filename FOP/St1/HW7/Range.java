// Reading the content of the file Range.java
public class Range {
    private int start;
    private int end;

    public Range(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot be greater than end.");
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }
}
