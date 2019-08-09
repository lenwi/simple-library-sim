package model.library;

import model.members.Member;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ui.LibraryFrontDesk.split;

public class Library implements Loadable, Saveable {

    static String SPLIT_CHAR = "\t";
    private Newspaper newspaper;
    private Book b1;
    private Book b2;
    private Book b3;
    private Book b4;
    private Book b5;

    private List<Member> members;
    private Map<String, ArrayList<Book>> hmMembers = new HashMap<>();
    private List<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
        b1 = new Book("The Lord of the Rings", "Fantasy");
        b2 = new Book("Murder on the Orient Express", "Mystery");
        b3 = new Book("It", "Horror");
        b4 = new Book("The Cat in the Hat", "Children's Book");
        b5 = new Book("Eragon", "Fantasy");

        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);

        this.newspaper = new Newspaper("UBC NEWS", "01", 10);
    }

    @Override
    // REQUIRES: a valid member
    // MODIFIES: this/file
    // EFFECTS: writes in member info to a text file
    public void writeFile(String file) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(file, true));
        for (Member m: members) {
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
    public List<Member> getMembers() {
        return members;
    }

    public List<Book> getBooks() {
        return books;
    }

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

        if (!bookList.contains(book)) {
            bookList.add(book);
            books.remove(book);
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
}
