package pgdp.retake;

import java.util.ArrayList;
import java.util.List;

public class Util {
    // Method to find all intervals covering a given point
    public static Interval[] allCovering(Interval[] obstacles, Point point) {
        List<Interval> coveringIntervals = new ArrayList<>();
        for (Interval obstacle : obstacles) {
            if (obstacle.covers(point)) {
                coveringIntervals.add(obstacle);
            }
        }
        return coveringIntervals.toArray(new Interval[0]); // Convert List to array
    }
}