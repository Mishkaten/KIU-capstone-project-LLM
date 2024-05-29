import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch {

    public static Result searchFile(Path file, String[] searched) {
        Result result = new Result(file);
        try (Stream<String> lines = Files.lines(file)) {
            List<String> allLines = lines.collect(Collectors.toList());
            for (int i = 0; i < allLines.size(); i++) {
                String line = allLines.get(i);
                for (String searchStr : searched) {
                    if (line.contains(searchStr)) {
                        result.addMatch(new Match(i, line));
                        break; // Break to avoid adding the same line multiple times
                    }
                }
            }
        } catch (IOException e) {
            return null; // Return null in case of exception
        }
        return result;
    }

    public static Set<Result> searchDirectory(Path directory, String[] searched) {
        Set<Result> results = new HashSet<>();
        try {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (Files.isRegularFile(file)) {
                        Result result = searchFile(file, searched);
                        if (result != null) {
                            results.add(result);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.println("Failed to visit file: " + file + " (" + exc.getMessage() + ")");
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Error walking the directory: " + e.getMessage());
        }
        return results;
    }

    public static List<Result> listResults(String directory, String[] searched) {
        Path dirPath = Paths.get(directory);
        Set<Result> resultsSet = searchDirectory(dirPath, searched);
        List<Result> resultsList = new ArrayList<>(resultsSet);
        resultsList.sort(Comparator.comparingInt((Result r) -> r.getMatches().size()).reversed());
        return resultsList;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java FileSearch <directory> <search strings...>");
            return;
        }

        String directory = args[0];
        String[] searched = Arrays.copyOfRange(args, 1, args.length);

        List<Result> results = listResults(directory, searched);
        results.forEach(result -> System.out.println(result.toString()));
    }
}
