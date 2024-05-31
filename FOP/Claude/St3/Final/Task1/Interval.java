package pgdp.retake;

public class Interval {

    private final int x;
    private final int y;
    private final int width;

    public Interval(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public boolean covers(Point point) {
        // TODO
        return false;
    }

    public boolean isCoveredBy(Interval[] obstacles) {
        // TODO
        return false;
    }

}
