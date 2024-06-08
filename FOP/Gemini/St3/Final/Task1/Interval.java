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

    // Method to check if a point is covered by the interval or its shadow
    public boolean covers(Point point) {
        return point.getX() >= this.x && point.getX() <= this.x + this.width && point.getY() >= this.y;
    }

    // Method to check if this interval is covered by any other intervals in an array
    public boolean isCoveredBy(Interval[] obstacles) {
        for (Interval obstacle : obstacles) {
            if (obstacle != this && obstacle.covers(new Point(this.x, this.y)) &&
                    obstacle.covers(new Point(this.x + this.width - 1, this.y))) {
                return true;
            }
        }
        return false;
    }

}
