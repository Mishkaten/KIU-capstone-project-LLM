import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Penguin {
    private String trackID;
    private List<Geo> locations;

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

    @Override
    public String toString() {
        return "Penguin{" +
                "locations=" + locations +
                ", trackID='" + trackID + '\'' +
                '}';
    }

    public String toStringUsingStreams() {
        List<Geo> sortedLocations = locations.stream()
                .sorted(Comparator.comparing(Geo::getLatitude).reversed()
                        .thenComparing(Geo::getLongitude).reversed())
                .collect(Collectors.toList());

        return "Penguin{" +
                "locations=" + sortedLocations +
                ", trackID='" + trackID + '\'' +
                '}';
    }
}
