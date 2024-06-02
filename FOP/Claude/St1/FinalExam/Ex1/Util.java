package St1.FinalExam.Ex1;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Interval[] allCovering(Interval[] obstacles, Point point) {
        List<Interval> coveringIntervals = new ArrayList<>();

        for (Interval interval : obstacles) {
            if (interval.covers(point)) {
                coveringIntervals.add(interval);
            }
        }

        return coveringIntervals.toArray(new Interval[0]);
    }

}
