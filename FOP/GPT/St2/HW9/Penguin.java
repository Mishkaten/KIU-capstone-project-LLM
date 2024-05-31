package GPT.St2.HW9;

import java.util.List;
import java.util.stream.Collectors;

public class Penguin {
    private final String trackID;
    private final List<Geo> locations;

    public Penguin(String trackID, List<Geo> locations) {
        this.trackID = trackID;
        this.locations = locations;
    }

    public String getTrackID() {
        return trackID;
    }

    public List<Geo> getLocations() {
        return locations;
    }

    public String toStringUsingStreams() {
        String locationsString = locations.stream()
                .sorted((g1, g2) -> {
                    int compareLat = Double.compare(g2.getLatitude(), g1.getLatitude());
                    if (compareLat != 0) return compareLat;
                    return Double.compare(g2.getLongitude(), g1.getLongitude());
                })
                .map(Geo::toString)
                .collect(Collectors.joining(", "));
        return "Penguin{locations=[" + locationsString + "], trackID='" + trackID + "'}";
    }
}
