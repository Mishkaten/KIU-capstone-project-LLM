package Claude.St3.HW10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FileSearch {
    public static Result searchFile(Path file, String[] searched) {
        try {
            Result result = new Result(file);
            int lineIndex = 0; // start from 0
            for (String line : Files.readAllLines(file)) {
                for (String searchString : searched) {
                    if (line.contains(searchString)) {
                        result.addMatch(new Match(lineIndex, line));
                        break; // Move to the next line if a match is found
                    }
                }
                lineIndex++;
            }
            return result;
        } catch (IOException e) {
            System.out.println("Error reading file: " + file); // specific error
            return null;
        }
    }

    public static Set<Result> searchDirectory(Path directory, String[] searched) {
        try {
            return Files.walk(directory)
                    .filter(Files::isRegularFile)  // Regular files only
                    .map(file -> searchFile(file, searched))
                    .filter(Objects::nonNull)   // Filter out null results (errors)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            System.out.println("Error searching directory: " + directory); // specific error
            return Collections.emptySet(); // Return empty set on error
        }
    }

    public static List<Result> listResults(String directory, String[] searched) {
        Path dirPath = Paths.get(directory);

        // Check if directory exists and is accessible
        if (!Files.isDirectory(dirPath)) {
            System.out.println("Invalid directory: " + directory);
            return Collections.emptyList(); // Return empty list
        }

        Set<Result> results = searchDirectory(dirPath, searched);
        List<Result> sortedResults = new ArrayList<>(results);

        // Sort results descending by match count
        sortedResults.sort(Comparator.comparingInt(result -> -result.getMatches().size())); // Negative for descending order
        return sortedResults;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: FileSearch <directory> <search_term1> <search_term2> ...");
            return;
        }
        List<Result> results = listResults(args[0], Arrays.copyOfRange(args, 1, args.length));
        results.forEach(System.out::println);
    }
}
