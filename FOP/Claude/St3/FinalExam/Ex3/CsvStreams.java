package St3.FinalExam.Ex3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class CsvStreams {

    public static void main(final String[] args) {
        final Stream<String> csvFilePaths;
        final Stream<String> lines;
        final Stream<Integer[]> rows;
        final Integer[] columnSums;

        csvFilePaths = filterCsvFilePaths(getFiles());
        lines = getAllLines(csvFilePaths);
        rows = parseLines(lines);
        columnSums = sumColumns(rows);

        System.out.println(Arrays.toString(columnSums));
    }

    static Stream<String> filterCsvFilePaths(final Stream<String> filePaths) {
        return filePaths.filter(filePath -> filePath.endsWith(".csv"));
    }

    static Stream<String> getAllLines(final Stream<String> csvFilePaths) {
        return csvFilePaths.flatMap(CsvStreams::getLinesOfFile);
    }

    static Stream<Integer[]> parseLines(final Stream<String> lines) {
        return lines
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 5)
                .map(CsvStreams::arrayToStream)
                .map(stream -> stream.map(Integer::parseInt).toArray(Integer[]::new));
    }

    static Integer[] sumColumns(final Stream<Integer[]> rows) {
        return rows.reduce(new Integer[5], CsvStreams::addArrays);
    }

    // Auxiliary methods possibly used for implementation!

    // returns the paths to all files in the current directory as Stream<String>.
    static Stream<String> getFiles() {
        try {
            return Files.list(Paths.get(".")).map(Path::toString);
        } catch (IOException e) {
            return Stream.of();
        }
    }

    // returns all lines in a file as Stream<String>.
    static Stream<String> getLinesOfFile(final String filePath) {
        try {
            return Files.lines(Path.of(filePath));
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    // adds element-wise two Integer arrays.
    static Integer[] addArrays(final Integer[] arr1, final Integer[] arr2) {
        return Stream.iterate(0, i -> i < 5, i -> i + 1)
                .map(i -> arr1[i] + arr2[i])
                .toArray(Integer[]::new);
    }

    // transforms an Integer Stream into an Integer array.
    static Integer[] intStreamToIntArray(final Stream<Integer> intStream) {
        return intStream.toArray(Integer[]::new);
    }

    // transforms an arbitrary array into a stream.
    static <T> Stream<T> arrayToStream(final T[] array) {
        return Arrays.stream(array);
    }
}