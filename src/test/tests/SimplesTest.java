package tests;

import model.library.Book;
import model.library.Library;
import model.library.Readings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimplesTest {
    private Library testLibrary;

    @BeforeEach
    public void setup() {
        testLibrary = new Library();
    }

    @Test
    public void testGetBook() {
        Book b1 = new Book("title", "genre");
        Book b2 = new Book("a", "b");
        List<Book> books = new ArrayList<Book>();
        assertEquals("title", b1.getTitle());
        assertEquals("genre", b1.getCategory());
        b1.setTitle("t");
        b1.setCategory("g");
        assertEquals("t", b1.getTitle());
        assertEquals("g", b1.getCategory());
        books.add(b1);
        assertTrue(books.contains(b1));
        testLibrary.getBooks().contains(b1);
    }

    @Test
    public void testSetTitle() {
        Readings b = new Book("1", "2");
        assertEquals("1", b.getTitle());
        b.setTitle("0");
        assertTrue(b.getTitle().equals("0"));
    }
}
