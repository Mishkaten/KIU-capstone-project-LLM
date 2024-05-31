package Claude.St1.HW10;

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
        try {
            List<String> lines = Files.readAllLines(file);
            Result result = new Result(file);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (String searchString : searched) {
                    if (line.contains(searchString)) {
                        result.addMatch(new Match(i, line));
                        break;
                    }
                }
            }
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    public static Set<Result> searchDirectory(Path directory, String[] searched) {
        try (Stream<Path> walk = Files.walk(directory)) {
            return walk.filter(Files::isRegularFile)
                    .map(file -> searchFile(file, searched))
                    .filter(result -> result != null)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            System.err.println("Error searching directory: " + e.getMessage());
            return Collections.emptySet();
        }
    }

    public static List<Result> listResults(String directory, String[] searched) {
        Path directoryPath = Path.of(directory);
        Set<Result> results = searchDirectory(directoryPath, searched);
        return results.stream()
                .sorted(Comparator.comparingInt(result -> -result.getMatches().size()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: FileSearch <directory> <search string1> [<search string2> ...]");
            System.exit(1);
        }

        String directory = args[0];
        String[] searched = new String[args.length - 1];
        System.arraycopy(args, 1, searched, 0, searched.length);

        List<Result> results = listResults(directory, searched);
        for (Result result : results) {
            System.out.println(result);
        }
    }
}