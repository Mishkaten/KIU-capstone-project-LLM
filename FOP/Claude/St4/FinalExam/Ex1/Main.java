package St4.FinalExam.Ex1;

public class Main {

    public static void main(String[] args) {
        // Create some sample intervals
        Interval interval1 = new Interval(1, 2, 5);
        Interval interval2 = new Interval(3, 4, 3);
        Interval interval3 = new Interval(6, 1, 4);
        Interval[] obstacles = {interval1, interval2, interval3};

        // Create some sample points
        Point point1 = new Point(2, 3);
        Point point2 = new Point(4, 5);
        Point point3 = new Point(7, 2);
        Point point4 = new Point(10, 3);

        // Test the covers method
        System.out.println("Point " + point1 + " covered by interval1: " + interval1.covers(point1));
        System.out.println("Point " + point2 + " covered by interval1: " + interval1.covers(point2));

        // Test the allCovering method
        Interval[] coveringIntervals = Util.allCovering(obstacles, point3);
        System.out.println("Intervals covering point " + point3 + ":");
        for (Interval interval : coveringIntervals) {
            System.out.println(interval.getX() + ", " + interval.getY() + ", " + interval.getWidth());
        }

        // Test the isCoveredBy method
        System.out.println("Interval2 covered by obstacles: " + interval2.isCoveredBy(obstacles));
        System.out.println("Interval3 covered by obstacles: " + interval3.isCoveredBy(obstacles));
    }

}