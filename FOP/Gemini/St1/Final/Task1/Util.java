package St1.Final.Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Util{
    public static Interval[] allCovering(Interval[] obstacles, Point point) {
        int count = 0;
        // Count the number of covering intervals
        for (Interval interval : obstacles) {
            if (interval.covers(point)) {
                count++;
            }
        }

        // Create an array of the correct size
        Interval[] coveringObstacles = new Interval[count];
        int index = 0;
        for (Interval interval : obstacles) {
            if (interval.covers(point)) {
                coveringObstacles[index++] = interval;
            }
        }
        return coveringObstacles;
    }
}