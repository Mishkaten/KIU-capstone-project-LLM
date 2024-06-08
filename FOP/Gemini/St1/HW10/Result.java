package St1.HW10;// Reading the content of the file Result.java
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Result {
    private Path file;
    private List<Match> matches;

    public Result(Path file) {
        this.file = file;
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public Path getFile() {
        return file;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        if (matches.isEmpty()) {
            return "No match in file " + file;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(matches.size()).append(" match").append(matches.size() > 1 ? "es" : "").append(" in file ").append(file).append(".\n");
        for (Match match : matches) {
            sb.append(match).append("\n");
        }
        return sb.toString();
    }
}
