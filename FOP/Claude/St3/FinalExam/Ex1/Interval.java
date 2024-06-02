package St3.FinalExam.Ex1;

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
        return (pointX >= x && pointX <= x + width && pointY >= y);
    }

    public boolean isCoveredBy(Interval[] obstacles) {
        for (Interval obstacle : obstacles) {
            if (obstacle.getX() <= x && obstacle.getX() + obstacle.getWidth() >= x + width && obstacle.getY() <= y) {
                return true;
            }
        }
        return false;
    }

}
