package St1.FinalExam.Ex1;

import St1.FinalExam.Ex1.Interval;
import St1.FinalExam.Ex1.Point;
import St1.FinalExam.Ex1.Util;

public class Main {
    public static void main(String[] args) {
        // Test case 1
        St1.FinalExam.Ex1.Interval[] obstacles1 = {
                new St1.FinalExam.Ex1.Interval(1, 1, 3),
                new St1.FinalExam.Ex1.Interval(5, 2, 4),
                new St1.FinalExam.Ex1.Interval(8, 0, 2)
        };
        St1.FinalExam.Ex1.Point point1 = new St1.FinalExam.Ex1.Point(2, 3);
        St1.FinalExam.Ex1.Interval[] coveringIntervals1 = St1.FinalExam.Ex1.Util.allCovering(obstacles1, point1);
        System.out.println("Test case 1:");
        System.out.println("Point: " + point1);
        System.out.println("Covering intervals:");
        for (St1.FinalExam.Ex1.Interval interval : coveringIntervals1) {
            System.out.println("x: " + interval.getX() + ", y: " + interval.getY() + ", width: " + interval.getWidth());
        }

        // Test case 2
        St1.FinalExam.Ex1.Interval[] obstacles2 = {
                new St1.FinalExam.Ex1.Interval(0, 0, 5),
                new St1.FinalExam.Ex1.Interval(3, 2, 4),
                new St1.FinalExam.Ex1.Interval(7, 1, 3)
        };
        St1.FinalExam.Ex1.Point point2 = new Point(6, 4);
        St1.FinalExam.Ex1.Interval[] coveringIntervals2 = Util.allCovering(obstacles2, point2);
        System.out.println("\nTest case 2:");
        System.out.println("Point: " + point2);
        System.out.println("Covering intervals:");
        for (St1.FinalExam.Ex1.Interval interval : coveringIntervals2) {
            System.out.println("x: " + interval.getX() + ", y: " + interval.getY() + ", width: " + interval.getWidth());
        }

        // Test case 3
        St1.FinalExam.Ex1.Interval interval1 = new Interval(2, 2, 4);
        System.out.println("\nTest case 3:");
        System.out.println("Interval: x: " + interval1.getX() + ", y: " + interval1.getY() + ", width: " + interval1.getWidth());
        System.out.println("Is covered by obstacles1: " + interval1.isCoveredBy(obstacles1));
        System.out.println("Is covered by obstacles2: " + interval1.isCoveredBy(obstacles2));
    }
}