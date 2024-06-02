package St3.FinalExam.Ex1;

public class Main {
    public static void main(String[] args) {
        // Test case 1
        Interval[] obstacles1 = {
                new Interval(1, 1, 3),
                new Interval(5, 2, 4),
                new Interval(8, 0, 2)
        };
        Point point1 = new Point(2, 3);
        Interval[] coveringIntervals1 = Util.allCovering(obstacles1, point1);
        System.out.println("Test case 1:");
        System.out.println("Point: " + point1);
        System.out.println("Covering intervals:");
        for (Interval interval : coveringIntervals1) {
            System.out.println("x: " + interval.getX() + ", y: " + interval.getY() + ", width: " + interval.getWidth());
        }

        // Test case 2
        Interval[] obstacles2 = {
                new Interval(0, 0, 5),
                new Interval(3, 2, 4),
                new Interval(7, 1, 3)
        };
        Point point2 = new Point(6, 4);
        Interval[] coveringIntervals2 = Util.allCovering(obstacles2, point2);
        System.out.println("\nTest case 2:");
        System.out.println("Point: " + point2);
        System.out.println("Covering intervals:");
        for (Interval interval : coveringIntervals2) {
            System.out.println("x: " + interval.getX() + ", y: " + interval.getY() + ", width: " + interval.getWidth());
        }

        // Test case 3
        Interval interval1 = new Interval(2, 2, 4);
        System.out.println("\nTest case 3:");
        System.out.println("Interval: x: " + interval1.getX() + ", y: " + interval1.getY() + ", width: " + interval1.getWidth());
        System.out.println("Is covered by obstacles1: " + interval1.isCoveredBy(obstacles1));
        System.out.println("Is covered by obstacles2: " + interval1.isCoveredBy(obstacles2));
    }
}