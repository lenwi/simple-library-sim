package tests;

import model.library.Newspaper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;


public class ExceptionsTest {

    @Test
    public void fileNotFoundException() {
        Newspaper testNewspaper = new Newspaper("t", "1", 5);
        try{
            testNewspaper.readFile("testnews.txt");
            fail("Exception should have been thrown, file does not exist");
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
