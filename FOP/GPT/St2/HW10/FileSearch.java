package GPT.St2.HW10;

import java.io.IOException;
import java.lang.invoke.CallSite;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class FileSearch {

    public static Result searchFile(Path file, String[] searched) {
        Result result = new Result(file);
        List<String> read = null;
        try {
            read = Files.readAllLines(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int i = 0;
        for (String line : read) {
            for (int k = 0; k < searched.length; k++) {
                if (line.contains(searched[k]))
                    result.addMatch(new Match(i + 1, line));

            }
            i++;
        }

        return result;
    }

    public static Set<Result> searchDirectory(Path directory, String searched[]) {
        Set<Result> result = null;
        try {
            Stream<Path> walk = Files.walk(directory);
            result = walk.filter(Files::isRegularFile).map(t -> searchFile(t, searched)).collect(Collectors.toSet());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error");
        }

        return result;
    }

    public static List<Result> listResults(String directory, String searched[]) {
        // TODO
        Path P = Paths.get(directory);
        List<Result> result = null;
        result.stream().sorted(Comparator.comparing(t -> t.getMatches().size())).collect(Collectors.toList());
        Collections.reverse(result);
        return result;
    }


    public static void main(String[] args) {
        if(args.length < 2) System.out.println("Not enough args!");
        for (Result i  : listResults(args[0], args)){
            System.out.println(i.toString());
        }
    }

}
