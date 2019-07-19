package ui;

import model.library.Book;
import model.library.Library;
import model.library.Newspaper;
import model.members.Member;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class LibraryFrontDesk {

    private static String FILENAME = "members.txt";
    private static String SPLIT_CHAR = "\t";

    private Library library;
    private Book b1;
    private Book b2;
    private Book b3;
    private Book b4;
    private Book b5;


    public static void main(String[] args) throws IOException {
        LibraryFrontDesk libraryfrontdesk = new LibraryFrontDesk();
        FrontDesk frontDesk = new FrontDesk(libraryfrontdesk.getLibrary());
        System.out.println("Welcome to the library!");

        frontDesk.handleInput();
        frontDesk.endProgram();

        System.out.println("Have a nice day!");
    }

    public LibraryFrontDesk() throws IOException {
        this.library = new Library();
        loadMembers();
        loadBooks();
    }

    // getters
    public Library getLibrary() { return library; }

    // MODIFIES: this
    // EFFECTS: loads members of the library
    private void loadMembers() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        for (String line: lines) {
            String[] parts = split(line);

            String name = parts[0];
            String agegroup = parts[1];

            System.out.println("Loaded member: " + name + ": " + agegroup);
        }
//        m1 = new Member("Bob");
//        m1.setAgeGroup(AgeGroup.ADULT);
//        m2 = new Member("Jill");
//        m2.setAgeGroup(AgeGroup.ADOLESCENCE);
//
//        library.addMember(m1);
//        library.addMember(m2);
    }

    public static String[] split(String line) { return line.split(SPLIT_CHAR); }

    // MODIFIES: this
    // EFFECTS: loads books in the library
    private void loadBooks() {
        b1 = new Book("The Lord of the Rings", "Fantasy");
        b2 = new Book("Murder on the Orient Express", "Mystery");
        b3 = new Book("It", "Horror");
        b4 = new Book("The Cat in the Hat", "Children's Book");
        b5 = new Book("Eragon", "Fantasy");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
        library.addBook(b5);
    }

}
