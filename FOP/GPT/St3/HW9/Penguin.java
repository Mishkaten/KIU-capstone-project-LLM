package GPT.St3.HW9;

import java.util.List;

public class Penguin {
    private List<Geo> locations;
    private String trackID;

    public Penguin(String trackID) {
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
        // TODO
        return "";
    }

    public void addLocation(Geo geo) {
    }
}
