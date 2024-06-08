package Claude.St3.HW10;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Define the directory to search and the search strings
        String directory = "path/to/search/directory"; // Replace with actual directory path
        String[] searched = {"searchString1", "searchString2"}; // Replace with actual search strings

        // Perform the search and list results
        List<Result> results = FileSearch.listResults(directory, searched);

        // Print the results
        for (Result result : results) {
            System.out.println(result);
        }
    }
}
