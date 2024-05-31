package GPT.St4.HW10;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch {
    public static Result searchFile(Path file, String[] searched) {
        Result result = new Result(file);
        try (Stream<String> lines = Files.lines(file)) {
            List<String> lineList = lines.collect(Collectors.toList());
            for (int i = 0; i < lineList.size(); i++) {
                String line = lineList.get(i);
                for (String searchString : searched) {
                    if (line.contains(searchString)) {
                        result.addMatch(new Match(i, line));
                        break;  // Only add the line once even if multiple strings match
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;  // Return null if an exception occurs
        }
        return result;
    }

    public static Set<Result> searchDirectory(Path directory, String[] searched) {
        Set<Result> results = new HashSet<>();
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        Result result = searchFile(file, searched);
                        if (result != null && !result.getMatches().isEmpty()) {
                            results.add(result);
                        }
                    });
        } catch (IOException e) {
            System.err.println("Error while searching directory: " + e.getMessage());
        }
        return results;
    }

    public static List<Result> listResults(String directory, String[] searched) {
        Path dirPath = Paths.get(directory);
        Set<Result> resultSet = searchDirectory(dirPath, searched);
        return resultSet.stream()
                .sorted(Comparator.comparingInt((Result r) -> r.getMatches().size()).reversed())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java FileSearch <directory> <search_string1> <search_string2> ... <search_stringN>");
            return;
        }
        String directory = args[0];
        String[] searchStrings = Arrays.copyOfRange(args, 1, args.length);
        List<Result> results = listResults(directory, searchStrings);
        results.forEach(System.out::println);
    }
}
