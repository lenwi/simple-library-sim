package model.library;

import model.members.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ui.LibraryFrontDesk.split;

public class Library implements Loadable, Saveable {

    static String FILENAME = "members.txt";
    static String SPLIT_CHAR = "\t";

    private List<Member> members;
    private Map<String, ArrayList<Book>> hmMembers = new HashMap<>();
    private List<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
    }

    @Override
    // REQUIRES: a valid member
    // MODIFIES: this/file
    // EFFECTS: writes in member info to a text file
    public void writeFile(String file) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        for (Member m: members) {
            System.out.println("Writing member details: " + m.getName());
            writer.println(m.getName() + SPLIT_CHAR + m.getAgeGroup());
        }
        writer.flush();
        writer.close();
    }

    @Override
    // REQUIRES: a valid file
    // MODIFIES: this/file
    // EFFECTS: reads members from a text file
    public void readFile(String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));

        for (String line: lines) {
            String[] parts = split(line);

            String name = parts[0];
            String agegroup = parts[1];

            System.out.println(name + ": " + agegroup);
        }
    }

    // getters
    public List<Member> getMembers() { return members; }
    public Map<String, ArrayList<Book>> getHmMembers() { return hmMembers; }
    public List<Book> getBooks() { return books; }
    public ArrayList<Book> getBookList(String s) {
        return hmMembers.get(s);
    }


    // MODIFIES: this
    // EFFECTS adds member m to the library
    public void addMember(Member m) {
        members.add(m);
    }

    // MODIFIES: this
    // EFFECTS adds key member m with value list to hashmap
    public void addMemberHash(String s) {
        hmMembers.put(s, new ArrayList<>());
    }

    // REQUIRES: b is not null
    // MODIFIES: this
    // EFFECTS: adds book b to the library
    public void addBook(Book b) {
        books.add(b);
    }

    // EFFECTS: prints out the titles of all books in bb
    public String displayBorrowedBooks(ArrayList<Book> bb) {
        if (bb.size() == 0) {
            return "";
        } else {
            for (Book b : bb) {
                return b.getTitle() + "\n";
            }
        }
        return "";
    }

    // EFFECTS: returns true if hashmap contains given key, false otherwise
    public boolean containsMember(String s) {
        if (hmMembers.containsKey(s)) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: book exists in library
    // EFFECTS: returns the book that matches given title
    public Book findBook(String title) {
        Book foundBook = null;
        for (Book b: books) {
            if (b.getTitle().equals(title)) {
                foundBook = b;
            }
        }
        return foundBook;
    }

    // MODIFIES: this and books
    // EFFECTS: adds book to member's borrowed list and removes it from the library
    //          creates a new borrowed booklist if non exists
    public synchronized List<Book> borrow(String user, Book book) {
        List<Book> bookList = hmMembers.get(user);

        // if list does not exist create it
        if(bookList == null) {
            bookList = new ArrayList<Book>();
            bookList.add(book);
        } else {
            // add if item is not already in list
            if (!bookList.contains(book)) {
                bookList.add(book);
                books.remove(book);
            }
        }
        return bookList;
    }

    // REQUIRES: book exists in borrowed booklist
    // EFFECTS: returns the book that matches given title
    public Book findBorrowedBook(String user, String title) {
        List<Book> bookList = hmMembers.get(user);
        Book foundBook = null;
        for (Book b: bookList) {
            if (b.getTitle().equals(title)) {
                foundBook = b;
            }
        }
        return foundBook;
    }

    // MODIFIES: this and books
    // EFFECTS: removes book from member's borrowed list and adds it to the library
    public List<Book> returnBook(String user, Book book) {
        List<Book> bookList = hmMembers.get(user);

        if (!books.contains(book)) {
            bookList.remove(book);
            books.add(book);
        }
        return bookList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(hmMembers, library.hmMembers) &&
                Objects.equals(books, library.books);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hmMembers, books);
    }
}
