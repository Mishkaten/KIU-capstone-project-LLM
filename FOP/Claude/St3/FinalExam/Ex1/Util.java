package St3.FinalExam.Ex1;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static Interval[] allCovering(Interval[] obstacles, Point point) {
        List<Interval> coveringIntervals = new ArrayList<>();
        for (Interval obstacle : obstacles) {
            if (obstacle.covers(point)) {
                coveringIntervals.add(obstacle);
            }
        }
        return coveringIntervals.toArray(new Interval[0]);
    }
}