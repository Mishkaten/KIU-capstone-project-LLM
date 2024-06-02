package St1.FinalExam.Ex1;

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

        // Check if the point lies within the interval
        if (pointX >= x && pointX <= x + width && pointY >= y) {
            return true;
        }

        // Check if the point lies in the shade of the interval
        if (pointX > x + width && pointY >= y) {
            return true;
        }

        return false;
    }

    public boolean isCoveredBy(Interval[] obstacles) {
        int leftX = x;
        int rightX = x + width;
        int bottomY = y;

        for (Interval obstacle : obstacles) {
            if (obstacle.getX() <= leftX && obstacle.getX() + obstacle.getWidth() >= rightX && obstacle.getY() <= bottomY) {
                return true;
            }
        }

        return false;
    }

}
