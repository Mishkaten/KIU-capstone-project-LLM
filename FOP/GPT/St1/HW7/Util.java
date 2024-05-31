package GPT.St1.HW7;// Reading the content of the file Util.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> readPasswordsFromFile(String filePath) {
        List<String> passwords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                passwords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passwords;
    }
}
