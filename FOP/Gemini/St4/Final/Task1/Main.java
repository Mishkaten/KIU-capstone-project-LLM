package pgdp.retake;

public class Main {
    public static void main(String[] args) {
        // Create Interval objects
        Interval interval1 = new Interval(0, 0, 5);  // Covers x from 0 to 5 and y >= 0
        Interval interval2 = new Interval(3, 1, 4);  // Covers x from 3 to 7 and y >= 1
        Interval interval3 = new Interval(6, 2, 3);  // Covers x from 6 to 9 and y >= 2

        // Create an array of intervals
        Interval[] intervals = {interval1, interval2, interval3};

        // Create Point objects
        Point point1 = new Point(4, 0);  // Within interval1
        Point point2 = new Point(5, 2);  // Within interval1 and interval2
        Point point3 = new Point(7, 3);  // Within interval2 and interval3
        Point point4 = new Point(10, 1); // Not within any interval

        // Test if the points are covered by intervals
        testPointCoverage(point1, intervals);
        // Expected output:
        // Testing coverage for point: Point [x=4, y=0]
        // Intervals covering the point:
        // Interval: x=0, y=0, width=5

        testPointCoverage(point2, intervals);
        // Expected output:
        // Testing coverage for point: Point [x=5, y=2]
        // Intervals covering the point:
        // Interval: x=0, y=0, width=5
        // Interval: x=3, y=1, width=4

        testPointCoverage(point3, intervals);
        // Expected output:
        // Testing coverage for point: Point [x=7, y=3]
        // Intervals covering the point:
        // Interval: x=3, y=1, width=4
        // Interval: x=6, y=2, width=3

        testPointCoverage(point4, intervals);
        // Expected output:
        // Testing coverage for point: Point [x=10, y=1]
        // No intervals cover the point.

        // Check if specific intervals are covered by the obstacles
        Interval testInterval1 = new Interval(2, 0, 4); // Not covered by any interval
        Interval testInterval2 = new Interval(3, 1, 4); // Covered by interval1

        System.out.println("Test interval 1 is covered by obstacles: " + testInterval1.isCoveredBy(intervals));
        // Expected output:
        // Test interval 1 is covered by obstacles: false

        System.out.println("Test interval 2 is covered by obstacles: " + testInterval2.isCoveredBy(intervals));
        // Expected output:
        // Test interval 2 is covered by obstacles: true
    }

    public static void testPointCoverage(Point point, Interval[] intervals) {
        System.out.println("\nTesting coverage for point: " + point);
        Interval[] coveringIntervals = Util.allCovering(intervals, point);
        if (coveringIntervals.length == 0) {
            System.out.println("No intervals cover the point.");
        } else {
            System.out.println("Intervals covering the point:");
            for (Interval interval : coveringIntervals) {
                System.out.println("Interval: x=" + interval.getX() + ", y=" + interval.getY() + ", width=" + interval.getWidth());
            }
        }
    }
}
