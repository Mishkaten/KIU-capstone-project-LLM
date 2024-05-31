package Claude.St1.HW9;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        return stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .entrySet().stream()
                .map(entry -> new Penguin(
                        entry.getValue().stream()
                                .map(PenguinData::getGeom)
                                .collect(Collectors.toList()),
                        entry.getKey()
                ));
    }

    public static void outputPenguinStream() {
        Stream<PenguinData> penguinDataStream = CSVReading.processInputFile();
        Stream<Penguin> penguinStream = getDataByTrackId(penguinDataStream);
        long count = penguinStream.count();

        System.out.println(count);

        penguinStream = getDataByTrackId(CSVReading.processInputFile());
        penguinStream.sorted(Comparator.comparing(Penguin::getTrackID))
                .forEach(penguin -> System.out.println(penguin.toStringUsingStreams()));
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    DoubleSummaryStatistics longitudeStats = entry.getValue().stream()
                            .mapToDouble(PenguinData::getLongitude)
                            .summaryStatistics();
                    DoubleSummaryStatistics latitudeStats = entry.getValue().stream()
                            .mapToDouble(PenguinData::getLatitude)
                            .summaryStatistics();

                    System.out.printf("%s%n", entry.getKey());
                    System.out.printf("Min Longitude: %f Max Longitude: %f Avg Longitude: %f Min Latitude: %f Max Latitude: %f Avg Latitude: %f%n",
                            longitudeStats.getMin(), longitudeStats.getMax(), longitudeStats.getAverage(),
                            latitudeStats.getMin(), latitudeStats.getMax(), latitudeStats.getAverage());
                });
    }

    public static void main(String[] args) {
        // TODO Test your implementation yourself
    }
}
