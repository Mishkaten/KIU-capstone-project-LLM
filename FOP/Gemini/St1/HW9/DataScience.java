package St1.HW9;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
    public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
        return stream.collect(Collectors.groupingBy(PenguinData::getTrackID,
                        Collectors.mapping(PenguinData::getGeom, Collectors.toList())))
                .entrySet().stream()
                .map(entry -> new Penguin(entry.getValue(), entry.getKey()));
    }

    public static void outputPenguinStream() {
        Stream<Penguin> penguins = getDataByTrackId(CSVReading.processInputFile());
        write(penguins.count()); // Output the number of penguins
        penguins = getDataByTrackId(CSVReading.processInputFile()); // Get the stream again
        penguins.sorted(Comparator.comparing(Penguin::getTrackID))
                .forEach(penguin -> write(penguin.toStringUsingStreams()));
    }

    public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
        stream.collect(Collectors.groupingBy(PenguinData::getTrackID))
                .forEach((trackId, data) -> {
                    double minLon = data.stream().mapToDouble(PenguinData::getLongitude).min().orElse(0);
                    double maxLon = data.stream().mapToDouble(PenguinData::getLongitude).max().orElse(0);
                    OptionalDouble avgLon = data.stream().mapToDouble(PenguinData::getLongitude).average();

                    double minLat = data.stream().mapToDouble(PenguinData::getLatitude).min().orElse(0);
                    double maxLat = data.stream().mapToDouble(PenguinData::getLatitude).max().orElse(0);
                    OptionalDouble avgLat = data.stream().mapToDouble(PenguinData::getLatitude).average();

                    write(String.format("%s\nMin Longitude: %.6f Max Longitude: %.6f Avg Longitude: %.6f Min Latitude: %.6f Max Latitude: %.6f Avg Latitude: %.6f",
                            trackId, minLon, maxLon, avgLon.orElse(0), minLat, maxLat, avgLat.orElse(0)));
                });
    }


    public static void main(String[] args) {
        // Define the path to the CSV file
        String csvFilePath = "OC_LPhillips_LittlePenguin_GPS_tracks_DATA.csv";

        try {
            // Read all lines from the CSV file
            List<String> lines = Files.readAllLines(Paths.get(csvFilePath));

            // Parse the CSV data into a list of PenguinData objects
            List<PenguinData> penguinDataList = lines.stream()
                    .skip(1) // Skip header line
                    .map(CSVReading::parseLineToPenguinData)
                    .collect(Collectors.toList());

            // Perform data science operations
            double averageDistance = calculateAverageDistance(penguinDataList);
            Geo boundingBox = calculateBoundingBox(penguinDataList);

            // Print results
            System.out.println("Average Distance: " + averageDistance);
            System.out.println("Bounding Box: " + boundingBox);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Example method to calculate the average distance from the penguin data
    private static double calculateAverageDistance(List<PenguinData> penguinDataList) {
        return penguinDataList.stream()
                .mapToDouble(PenguinData::getDistance)
                .average()
                .orElse(0.0);
    }

    // Example method to calculate the bounding box from the penguin data
    private static Geo calculateBoundingBox(List<PenguinData> penguinDataList) {
        double minLatitude = penguinDataList.stream().mapToDouble(PenguinData::getLatitude).min().orElse(0.0);
        double maxLatitude = penguinDataList.stream().mapToDouble(PenguinData::getLatitude).max().orElse(0.0);
        double minLongitude = penguinDataList.stream().mapToDouble(PenguinData::getLongitude).min().orElse(0.0);
        double maxLongitude = penguinDataList.stream().mapToDouble(PenguinData::getLongitude).max().orElse(0.0);
        return new Geo(minLatitude, maxLatitude, minLongitude, maxLongitude);
    }

}
