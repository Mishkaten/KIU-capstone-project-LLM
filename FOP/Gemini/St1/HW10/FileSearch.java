package St1.HW10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileSearch {

    public static Result searchFile(Path file, String[] searched) {
        Result result = new Result(file);
        try (Stream<String> lines = Files.lines(file)) {
            int lineIndex = 0;
            for (String line : lines.collect(Collectors.toList())) {
                for (String searchString : searched) {
                    if (line.contains(searchString)) {
                        result.addMatch(new Match(lineIndex, line));
                        break; // Move to the next line if a match is found
                    }
                }
                lineIndex++;
            }

        } catch (IOException e) {
            System.err.println("Error while reading file: " + file + " - " + e.getMessage());
            return null; // Return null in case of an exception
        }
        return result;
    }

    // List results in descending order of match count
    public static List<Result> listResults(String directory, String[] searched) {
        Path dirPath = Paths.get(directory);
        Set<Result> resultSet = searchDirectory(dirPath, searched);
        List<Result> results = new ArrayList<>(resultSet); // convert to list for sorting
        results.sort(Comparator.comparing(Result::numMatches).reversed());
        return results;
    }

    // Search a directory and its subdirectories
    public static Set<Result> searchDirectory(Path directory, String[] searched) {
        Set<Result> results = new HashSet<>();
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.filter(Files::isRegularFile)
                    .map(file -> searchFile(file, searched))
                    .filter(Objects::nonNull) // Filter out null results (errors)
                    .forEach(results::add);
        } catch (IOException e) {
            System.err.println("Error while searching directory: " + e.getMessage());
        }
        return results;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java FileSearch <directory> <search_string1> [<search_string2> ...]");
            return;
        }

        String directory = args[0];
        String[] searched = Arrays.copyOfRange(args, 1, args.length);

        List<Result> results = listResults(directory, searched);
        results.forEach(System.out::println); // Print each result with a line break
    }
}