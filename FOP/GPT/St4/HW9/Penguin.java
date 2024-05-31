package GPT.St4.HW9;

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
                .sorted((geo1, geo2) -> {
                    int latCompare = Double.compare(geo2.getLatitude(), geo1.getLatitude());
                    return latCompare != 0 ? latCompare : Double.compare(geo2.getLongitude(), geo1.getLongitude());
                })
                .map(Geo::toString)
                .collect(Collectors.joining(", "));
        return "Penguin{locations=[" + locationsString + "], trackID='" + trackID + "'}";
    }
}
