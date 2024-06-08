package Claude.St3.HW9;

import java.util.List;
import java.util.stream.Collectors;

public class Penguin {
    private List<Geo> locations;
    private String trackID;

    public Penguin(List<Geo> locations, String trackID) {
        this.locations = locations;
        this.trackID = trackID;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "locations=" + locations +
                ", trackID='" + trackID + '\'' +
                '}';
    }

    public List<Geo> getLocations() {
        return locations;
    }

    public String getTrackID() {
        return trackID;
    }

    public String toStringUsingStreams() {
        return "Penguin{locations=[" +
                locations.stream()
                        .sorted((g1, g2) -> {
                            int cmp = Double.compare(g2.getLatitude(), g1.getLatitude());
                            if (cmp == 0) {
                                cmp = Double.compare(g2.getLongitude(), g1.getLongitude());
                            }
                            return cmp;
                        })
                        .map(Geo::toString)
                        .collect(Collectors.joining(", "))
                + "], trackID='" + trackID + "'}";
    }

}
