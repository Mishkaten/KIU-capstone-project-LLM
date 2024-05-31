package Claude.St1.HW10;

// Reading the content of the file Match.java
public class Match {
    private int lineIndex;
    private String line;

    public Match(int lineIndex, String line) {
        this.lineIndex = lineIndex;
        this.line = line;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Match in line " + lineIndex + ": " + line;
    }
}
