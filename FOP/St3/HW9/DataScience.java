import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        Map<String, List<Geo>> groupedByTrackID = stream.collect(Collectors.groupingBy(
                PenguinData::getTrackID,
                Collectors.mapping(pd -> new Geo(pd.getLatitude(), pd.getLongitude()), Collectors.toList())
        ));

        return groupedByTrackID.entrySet().stream()
                .map(entry -> {
                    Penguin penguin = new Penguin(entry.getKey());
                    entry.getValue().forEach(penguin::addLocation);
                    return penguin;
                });
    }

    public static void outputPenguinStream() {
        Stream<PenguinData> penguinDataStream = CSVReading.processInputFile("path/to/your/csvfile.csv");
        Stream<Penguin> penguinStream = getDataByTrackId(penguinDataStream);

        List<Penguin> penguins = penguinStream.sorted(Comparator.comparing(Penguin::getTrackID)).collect(Collectors.toList());

        System.out.println(penguins.size());
        penguins.forEach(p -> System.out.println(p.toStringUsingStreams()));
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        Map<String, List<Geo>> groupedByTrackID = stream.collect(Collectors.groupingBy(
                PenguinData::getTrackID,
                Collectors.mapping(pd -> new Geo(pd.getLatitude(), pd.getLongitude()), Collectors.toList())
        ));

        groupedByTrackID.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String trackID = entry.getKey();
                    List<Geo> locations = entry.getValue();

                    double minLongitude = locations.stream().mapToDouble(Geo::getLongitude).min().orElse(Double.NaN);
                    double maxLongitude = locations.stream().mapToDouble(Geo::getLongitude).max().orElse(Double.NaN);
                    double avgLongitude = locations.stream().mapToDouble(Geo::getLongitude).average().orElse(Double.NaN);

                    double minLatitude = locations.stream().mapToDouble(Geo::getLatitude).min().orElse(Double.NaN);
                    double maxLatitude = locations.stream().mapToDouble(Geo::getLatitude).max().orElse(Double.NaN);
                    double avgLatitude = locations.stream().mapToDouble(Geo::getLatitude).average().orElse(Double.NaN);

                    System.out.println(trackID);
                    System.out.printf("Min Longitude: %f Max Longitude: %f Avg Longitude: %f Min Latitude: %f Max Latitude: %f Avg Latitude: %f%n",
                            minLongitude, maxLongitude, avgLongitude, minLatitude, maxLatitude, avgLatitude);
                });
    }
}
