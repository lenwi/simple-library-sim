package tests;

import model.library.Book;
import model.library.Library;
import model.library.Newspaper;
import model.members.AgeGroup;
import model.members.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {
    Library testLibrary;

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

}
