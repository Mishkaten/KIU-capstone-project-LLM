package Claude.St3.HW9;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        return stream
                .collect(Collectors.groupingBy(PenguinData::getTrackID)) // Group by trackID
                .entrySet().stream()
                .map(entry -> new Penguin(
                        entry.getValue().stream()
                                .map(PenguinData::getGeom)
                                .collect(Collectors.toList()),
                        entry.getKey()
                ));
    }

    public static void outputPenguinStream() {
        Stream<Penguin> penguinStream = getDataByTrackId(CSVReading.processInputFile());

        // Count penguins and output the count
        long count = penguinStream.count();
        System.out.println(count);

        // Re-create the stream to iterate over it again
        penguinStream = getDataByTrackId(CSVReading.processInputFile());

        // Output penguins sorted by trackID
        penguinStream
                .sorted(Comparator.comparing(Penguin::getTrackID))
                .forEach(penguin -> System.out.println(penguin.toStringUsingStreams()));
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .forEach((trackID, data) -> {
                    double minLat = data.stream().mapToDouble(PenguinData::getLatitude).min().orElse(0);
                    double maxLat = data.stream().mapToDouble(PenguinData::getLatitude).max().orElse(0);
                    double avgLat = data.stream().mapToDouble(PenguinData::getLatitude).average().orElse(0);
                    double minLon = data.stream().mapToDouble(PenguinData::getLongitude).min().orElse(0);
                    double maxLon = data.stream().mapToDouble(PenguinData::getLongitude).max().orElse(0);
                    double avgLon = data.stream().mapToDouble(PenguinData::getLongitude).average().orElse(0);

                    System.out.printf("%s\nMin Longitude: %.6f Max Longitude: %.6f Avg Longitude: %.9f Min Latitude: %.6f Max Latitude: %.6f Avg Latitude: %.9f\n",
                            trackID, minLon, maxLon, avgLon, minLat, maxLat, avgLat);
                });
    }

    public static void main(String[] args) {
        Stream<PenguinData> stream = CSVReading.processInputFile();
        outputPenguinStream();
        outputLocationRangePerTrackid(stream);
    }
}
