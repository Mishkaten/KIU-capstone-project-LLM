package Claude.St3.HW9;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
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
        Stream<PenguinData> stream = CSVReading.processInputFile();
        List<Penguin> penguins = getDataByTrackId(stream)
                .sorted(Comparator.comparing(Penguin::getTrackID))
                .collect(Collectors.toList());

        System.out.println(penguins.size());
        penguins.forEach(penguin -> System.out.println(penguin.toStringUsingStreams()));
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

                    System.out.println(entry.getKey());
                    System.out.printf("Min Longitude: %f Max Longitude: %f Avg Longitude: %f Min Latitude: %f Max Latitude: %f Avg Latitude: %f%n",
                            longitudeStats.getMin(), longitudeStats.getMax(), longitudeStats.getAverage(),
                            latitudeStats.getMin(), latitudeStats.getMax(), latitudeStats.getAverage());
                });
    }

    public static void main(String[] args) {
        Stream<PenguinData> stream = CSVReading.processInputFile();
        outputPenguinStream();
        outputLocationRangePerTrackid(stream);
    }
}
