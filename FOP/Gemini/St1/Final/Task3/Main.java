package St1.Final.Task3;

import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create test CSV files
        createTestCsvFiles();

        // Test CsvStreams methods
        Stream<String> csvFilePaths = CsvStreams.filterCsvFilePaths(CsvStreams.getFiles());
        Stream<String> lines = CsvStreams.getAllLines(csvFilePaths);
        Stream<Integer[]> rows = CsvStreams.parseLines(lines);
        Integer[] columnSums = CsvStreams.sumColumns(rows);

        System.out.println("Column Sums: " + Arrays.toString(columnSums));
        // Expected output: Column Sums: [6, 9, 12, 15, 18]

        // Clean up test files
        cleanUpTestCsvFiles();
    }

    private static void createTestCsvFiles() throws IOException {
        List<String> csv1 = Arrays.asList(
                "1,2,3,4,5",
                "1,2,3,4,5"
        );
        List<String> csv2 = Arrays.asList(
                "1,2,3,4,5",
                "1,2,3,4,5"
        );

        Files.write(Paths.get("test1.csv"), csv1);
        Files.write(Paths.get("test2.csv"), csv2);
    }

    private static void cleanUpTestCsvFiles() throws IOException {
        Files.deleteIfExists(Paths.get("test1.csv"));
        Files.deleteIfExists(Paths.get("test2.csv"));
    }
}
