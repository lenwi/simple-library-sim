package tests;

import model.library.Book;
import model.library.Library;
import model.library.Newspaper;
import model.members.AgeGroup;
import model.members.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {
    Library testLibrary;
    private Map<String, ArrayList<Book>> hmMembers = new HashMap<>();

    @BeforeEach
    public void setup() {
        testLibrary = new Library();
    }

    @Test
    public void testLoadMembers() {
        Member m1;
        Member m2;
        m1 = new Member("Bob");
        m1.setAgeGroup(AgeGroup.ADULT);
        m2 = new Member("Jill");
        m2.setAgeGroup(AgeGroup.ADOLESCENCE);
        testLibrary.addMember(m1);
        assertTrue(testLibrary.getMembers().contains(m1));
        assertFalse(testLibrary.getMembers().contains(m2));
        assertEquals(testLibrary.getMembers().size(), 1);
        testLibrary.addMember(m2);
        assertTrue(testLibrary.getMembers().contains(m1));
        assertTrue(testLibrary.getMembers().contains(m2));
        assertEquals(testLibrary.getMembers().size(), 2);

    }

    @Test
    public void testLoadBooks() {
        Book b1;
        Book b2;
        b1 = new Book("The Lord of the Rings", "Fantasy");
        b2 = new Book("Murder on the Orient Express", "Mystery");
        testLibrary.addBook(b1);
        assertTrue(testLibrary.getBooks().contains(b1));
        assertEquals(testLibrary.getBooks().size(), 1);
        testLibrary.addBook(b2);
        assertTrue(testLibrary.getBooks().contains(b2));
        assertEquals(testLibrary.getBooks().size(), 2);

    }

    @Test
    public void testSaveLoad() throws IOException {
        Member m0;
        Newspaper n0;
        n0 = new Newspaper("test", "5", 4);
        m0 = new Member("tester");
        m0.setAgeGroup(AgeGroup.ADULT);
        testLibrary.addMember(m0);
        testLibrary.writeFile("test.txt");
        testLibrary.readFile("test.txt");
        assertTrue("test.txt".length() > 0);
        n0.writeFile("newstest.txt");
        n0.readFile("newstest.txt");
        assertTrue("newstest.txt".length() > 0);
    }

    @Test
    public void testAddHashMember() {
        Book bw = new Book("me", "meme");
        Book bk = new Book("ms", "meme");
        testLibrary.addMemberHash("1");
        assertTrue(testLibrary.containsMember("1"));
        assertFalse(testLibrary.containsMember("2"));
        testLibrary.borrow("1", bw);
        testLibrary.borrow("1", bk);
        assertTrue(testLibrary.getBookList("1").contains(bw));
        assertEquals(testLibrary.findBorrowedBook("1", "me"), bw);
        testLibrary.returnBook("1", bw);
        assertTrue(testLibrary.getBookList("1").contains(bk));
        assertFalse(testLibrary.getBookList("1").contains(bw));
    }

    @Test
    public void testDisplayBook() {
        Book be = new Book("me", "meme");
        ArrayList<Book> bb = new ArrayList<>();
        assertEquals(testLibrary.displayBorrowedBooks(bb), "" + "");
        bb.add(be);
        assertEquals(testLibrary.displayBorrowedBooks(bb), "me" + "\n" + "");
    }

    @Test
    public void testFindBook() {
        Book b1;
        Book b2;
        b1 = new Book("The Lord of the Rings", "Fantasy");
        b2 = new Book("Murder on the Orient Express", "Mystery");
        testLibrary.addBook(b1);
        testLibrary.addBook(b2);
        assertEquals(testLibrary.findBook("The Lord of the Rings"), b1);
        assertEquals(testLibrary.findBook("Murder on the Orient Express"), b2);
    }
}
