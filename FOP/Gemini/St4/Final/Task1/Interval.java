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
        return (point.getX() >= x && point.getX() <= x + width && point.getY() >= y);
    }

    public boolean isCoveredBy(Interval[] obstacles) {
        for (Interval obstacle : obstacles) {
            if (obstacle.covers(new Point(x, y)) &&
                    obstacle.covers(new Point(x + width, y))) {
                return true; // Entire interval is covered by this obstacle
            }
        }
        return false; // No obstacle covers the entire interval
    }

}
