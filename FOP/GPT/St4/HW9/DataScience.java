package GPT.St4.HW9;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        return stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .entrySet().stream()
                .map(entry -> new Penguin(entry.getKey(), entry.getValue().stream()
                        .map(pd -> new Geo(pd.getLatitude(), pd.getLongitude()))
                        .collect(Collectors.toList())));
    }

    public static void outputPenguinStream() {
        try (Stream<PenguinData> dataStream = CSVReading.processInputFile()) {
            List<Penguin> penguins = getDataByTrackId(dataStream).sorted(Comparator.comparing(Penguin::getTrackID)).collect(Collectors.toList());

            System.out.println(penguins.size());
            penguins.forEach(penguin -> System.out.println(penguin.toStringUsingStreams()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    String trackID = entry.getKey();
                    List<PenguinData> dataPoints = entry.getValue();

                    DoubleSummaryStatistics latitudeStats = dataPoints.stream().mapToDouble(PenguinData::getLatitude).summaryStatistics();
                    DoubleSummaryStatistics longitudeStats = dataPoints.stream().mapToDouble(PenguinData::getLongitude).summaryStatistics();

                    System.out.println(trackID);
                    System.out.println("Min Longitude: " + longitudeStats.getMin() + " Max Longitude: " + longitudeStats.getMax() + " Avg Longitude: " + longitudeStats.getAverage());
                    System.out.println("Min Latitude: " + latitudeStats.getMin() + " Max Latitude: " + latitudeStats.getMax() + " Avg Latitude: " + latitudeStats.getAverage());
                });
    }

    public static void main(String[] args) {
        // Test your implementation yourself
        outputPenguinStream();

        try (Stream<PenguinData> dataStream = CSVReading.processInputFile()) {
            outputLocationRangePerTrackid(dataStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
