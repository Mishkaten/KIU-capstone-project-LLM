package de.tum.eidi1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
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

    // Filters CSV file paths
    static Stream<String> filterCsvFilePaths(final Stream<String> filePaths) {
        return filePaths.filter(x -> x.endsWith(".csv"));
    }

    // Gets all lines from all CSV files
    static Stream<String> getAllLines(final Stream<String> csvFilePaths) {
        return csvFilePaths.flatMap(CsvStreams::getLinesOfFile); // Use method reference
    }


    // Parses lines into Integer arrays, filtering out invalid lines
    static Stream<Integer[]> parseLines(final Stream<String> lines) {
        return lines
                .map(line -> Arrays.stream(line.split(","))        // Split into string arrays
                        .map(Integer::parseInt)          // Parse to Integers
                        .toArray(Integer[]::new));         // Convert to Integer arrays
    }

    // Sums the columns of the Integer arrays
    static Integer[] sumColumns(final Stream<Integer[]> rows) {
        Integer[] initialSum = {0, 0, 0, 0, 0};  // Initial array of zeros
        return rows.reduce(initialSum, CsvStreams::addArrays); // Accumulate sums
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
        Integer[] res = new Integer[5];
        for (int i = 0; i < 5; i++)
            res[i] = arr1[i] + arr2[i];
        return res;
    }

    // transforms an Integer Stream into an Integer array.
    static Integer[] intStreamToIntArray(final Stream<Integer> intStream) {
        return intStream.toArray(Integer[]::new);
    }

    // transforma an arbitrary array into a stream.
    static <T> Stream<T> arrayToStream(final T[] array) {
        return Arrays.stream(array);
    }
}
