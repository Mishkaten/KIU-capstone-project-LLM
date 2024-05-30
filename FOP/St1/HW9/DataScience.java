import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {

    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        Map<String, List<Geo>> groupedByTrackId = stream.collect(Collectors.groupingBy(
                PenguinData::getTrackID,
                Collectors.mapping(PenguinData::getLocation, Collectors.toList())
        ));

        return groupedByTrackId.entrySet().stream()
                .map(entry -> new Penguin(entry.getKey(), entry.getValue()));
    }

    public static void outputPenguinStream() {
        Stream<PenguinData> penguinDataStream = CSVReading.processInputFile("path_to_csv_file.csv");
        List<Penguin> penguins = getDataByTrackId(penguinDataStream).sorted(Comparator.comparing(Penguin::getTrackID)).collect(Collectors.toList());

        System.out.println(penguins.size());
        for (Penguin penguin : penguins) {
            System.out.println(penguin.toStringUsingStreams());
        }
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        Map<String, List<Geo>> groupedByTrackId = stream.collect(Collectors.groupingBy(
                PenguinData::getTrackID,
                Collectors.mapping(PenguinData::getLocation, Collectors.toList())
        ));

        groupedByTrackId.entrySet().stream()
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

                    System.out.printf("%s%n", trackID);
                    System.out.printf("Min Longitude: %f Max Longitude: %f Avg Longitude: %f Min Latitude: %f Max Latitude: %f Avg Latitude: %f%n",
                            minLongitude, maxLongitude, avgLongitude, minLatitude, maxLatitude, avgLatitude);
                });
    }

    public static void main(String[] args) {
        // Example call to output penguin data
        outputPenguinStream();

        // Example call to output location range per trackID
        Stream<PenguinData> penguinDataStream = CSVReading.processInputFile("path_to_csv_file.csv");
        outputLocationRangePerTrackid(penguinDataStream);
    }
}
