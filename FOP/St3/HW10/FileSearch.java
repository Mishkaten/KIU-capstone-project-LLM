import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch {

    public Result searchFile(Path file, String[] searched) {
        Result result = new Result(file);

        try {
            List<String> lines = Files.readAllLines(file);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (String searchString : searched) {
                    if (line.contains(searchString)) {
                        result.addMatch(new Match(i, line));
                        break; // Add the line only once
                    }
                }
            }
        } catch (IOException e) {
            return null; // Return null in case of an exception
        }

        return result;
    }

    public Set<Result> searchDirectory(Path directory, String[] searched) {
        Set<Result> results = new HashSet<>();

        try (Stream<Path> paths = Files.walk(directory)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                Result result = searchFile(file, searched);
                if (result != null) {
                    results.add(result);
                }
            });
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
        }

        return results;
    }

    public List<Result> listResults(String directory, String[] searched) {
        Path dirPath = Paths.get(directory);
        Set<Result> results = searchDirectory(dirPath, searched);

        return results.stream()
                .sorted(Comparator.comparingInt((Result r) -> r.getMatches().size()).reversed())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java FileSearch <directory> <search strings...>");
            System.exit(1);
        }

        String directory = args[0];
        String[] searchStrings = Arrays.copyOfRange(args, 1, args.length);

        FileSearch fileSearch = new FileSearch();
        List<Result> results = fileSearch.listResults(directory, searchStrings);

        results.forEach(result -> System.out.println(result.toString()));
    }
}
