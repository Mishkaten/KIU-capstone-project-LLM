import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch {

    public static Result searchFile(Path file, String[] searched) {
        Result result = new Result(file);
        try {
            List<String> lines = Files.readAllLines(file);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (String searchString : searched) {
                    if (line.contains(searchString)) {
                        result.addMatch(new Match(i, line));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public static Set<Result> searchDirectory(Path directory, String[] searched) {
        Set<Result> results = new HashSet<>();
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                Result result = searchFile(file, searched);
                if (result != null && !result.getMatches().isEmpty()) {
                    results.add(result);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<Result> listResults(String directory, String[] searched) {
        Path dirPath = Paths.get(directory);
        Set<Result> results = searchDirectory(dirPath, searched);
        return results.stream()
                .sorted((r1, r2) -> Integer.compare(r2.getMatches().size(), r1.getMatches().size()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileSearch <directory> <searched strings...>");
            return;
        }

        String directory = args[0];
        String[] searched = Arrays.copyOfRange(args, 1, args.length);

        List<Result> results = listResults(directory, searched);
        for (Result result : results) {
            System.out.print(result);
        }
    }
}
