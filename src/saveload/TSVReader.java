package saveload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TSVReader {
    static String FILENAME = "members.txt";
    static String SPLIT_CHAR = "\t";

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        for (String line: lines) {
            String[] parts = split(line);

            String name = parts[0];
            String agegroup = parts[1];

            System.out.println("Loaded member: " + name + ": " + agegroup);
        }
    }

    public static String[] split(String line) { return line.split(SPLIT_CHAR); }
}
