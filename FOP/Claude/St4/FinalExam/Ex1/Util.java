package St4.FinalExam.Ex1;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static Interval[] allCovering(Interval[] obstacles, Point point) {
        List<Interval> covering = new ArrayList<>();
        for (Interval obstacle : obstacles) {
            if (obstacle.covers(point)) {
                covering.add(obstacle);
            }
        }
        return covering.toArray(new Interval[0]);
    }

}