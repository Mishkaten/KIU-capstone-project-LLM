package St1.Final.Task1;

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
        int pointX = point.getX();
        int pointY = point.getY();

        // Check if the point is within the interval's horizontal range
        boolean withinInterval = pointX >= x && pointX <= x + width;

        // Check if the point is below the interval (in the shadow)
        boolean belowInterval = pointY >= y;

        // The point is covered if either condition is true
        return withinInterval || belowInterval;
    }

    public boolean isCoveredBy(Interval[] obstacles) {
        for (Interval obstacle : obstacles) {
            // Check if the current obstacle completely covers this interval
            if (obstacle.x <= this.x && obstacle.x + obstacle.width >= this.x + this.width && obstacle.y <= this.y) {
                return true;
            }
        }
        return false; // No obstacle covers this interval
    }

}
