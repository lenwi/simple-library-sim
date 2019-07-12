package ui;

import model.library.Book;
import model.library.Library;
import model.members.AgeGroup;
import model.members.Member;




public class LibraryFrontDesk {

    private Library library;
    private Member m1;
    private Member m2;
    private Book b1;
    private Book b2;
    private Book b3;
    private Book b4;


    public static void main(String[] args) {
        LibraryFrontDesk libraryfrontdesk = new LibraryFrontDesk();
        FrontDesk frontDesk = new FrontDesk(libraryfrontdesk.getLibrary());
        System.out.println("Welcome to the library!");

        frontDesk.handleInput();
        frontDesk.endProgram();

        System.out.println("Have a nice day!");
    }

    public LibraryFrontDesk() {
        library = new Library();
        loadMembers();
        loadBooks();
    }

    // getters
    public Library getLibrary() { return library; }

    // MODIFIES: this
    // EFFECTS: loads members of the library
    private void loadMembers() {
        m1 = new Member("Bob");
        m1.setAgeGroup(AgeGroup.ADULT);
        m2 = new Member("Jill");
        m2.setAgeGroup(AgeGroup.ADOLESCENCE);

        library.addMember(m1);
        library.addMember(m2);
    }

    // MODIFIES: this
    // EFFECTS: loads books in the library
    private void loadBooks() {
        b1 = new Book("The Lord of the Rings", "Fantasy");
        b2 = new Book("Murder on the Orient Express", "Mystery");
        b3 = new Book("It", "Horror");
        b4 = new Book("The Cat in the Hat", "Children's Book");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);
        library.addBook(b4);
    }

}
