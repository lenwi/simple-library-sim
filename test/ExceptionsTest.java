import model.library.Newspaper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class ExceptionsTest {

    @Test
    public void fileNotFoundException() {
        Newspaper testNewspaper = new Newspaper("t", "1", 5);
        try{
            testNewspaper.readFile("testnews.txt");
        } catch (IOException e) {
            // Supposed to be caught
        }
    }

    @Test
    public void fileExists() {
        Newspaper testNewspaper = new Newspaper("t", "1", 5);
        try{
            testNewspaper.readFile("newspaper.txt");
        } catch (IOException e) {
            fail("File exists yet it was treated as not exist.");
        }
    }
}
