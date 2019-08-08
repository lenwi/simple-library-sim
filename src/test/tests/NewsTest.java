package tests;

import model.library.Newspaper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewsTest {

    @Test
    public void testTakeNews() {
        Newspaper n0 = new Newspaper("", "1",2);
        n0.takeNewspaper();
        assertTrue(n0.getQuantity() == 1);
        assertEquals(n0.getCategory(),"Issue # " + 1);
        n0.takeNewspaper();
        assertTrue(n0.getQuantity() == 0);
        assertEquals(n0.getCategory(),"Issue # " + 1);
        n0.takeNewspaper();
        assertTrue(n0.getQuantity() == 9);
        assertEquals(n0.getCategory(),"Issue # " + 2);
    }
}
