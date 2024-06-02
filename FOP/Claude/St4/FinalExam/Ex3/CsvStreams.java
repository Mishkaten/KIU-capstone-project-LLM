package St4.FinalExam.Ex3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CsvStreams {

    public static void main(String[] args) {
        Stream<String> csvFilePaths = filterCsvFilePaths(getFiles());
        Stream<String> lines = getAllLines(csvFilePaths);
        Stream<Integer[]> rows = parseLines(lines);
        Integer[] columnSums = sumColumns(rows);

        System.out.println(Arrays.toString(columnSums));
    }

    static Stream<String> filterCsvFilePaths(Stream<String> filePaths) {
        return filePaths.filter(path -> path.toLowerCase().endsWith(".csv"));
    }

    static Stream<String> getAllLines(Stream<String> csvFilePaths) {
        return csvFilePaths.flatMap(CsvStreams::getLinesOfFile);
    }

    static Stream<Integer[]> parseLines(Stream<String> lines) {
        return lines.map(line -> line.split(","))
                .filter(parts -> parts.length == 5)
                .map(CsvStreams::parseIntArray);
    }

    static Integer[] sumColumns(Stream<Integer[]> rows) {
        return rows.reduce(new Integer[5], CsvStreams::addArrays);
    }

    // Auxiliary methods used for implementation

    static Stream<String> getFiles() {
        try {
            return Files.list(Paths.get(".")).map(Path::toString);
        } catch (IOException e) {
            return Stream.of();
        }
    }

    static Stream<String> getLinesOfFile(String filePath) {
        try {
            return Files.lines(Path.of(filePath));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    static Integer[] addArrays(Integer[] arr1, Integer[] arr2) {
        return Stream.iterate(0, i -> i + 1)
                .limit(arr1.length)
                .map(i -> arr1[i] + arr2[i])
                .toArray(Integer[]::new);
    }

    static Integer[] parseIntArray(String[] parts) {
        return Arrays.stream(parts)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
    }
}