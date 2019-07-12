package ui;

import model.library.Library;
import model.members.AgeGroup;
import model.members.Member;




public class LibraryFrontDesk {

    private Library library;
    private Member m1;
    private Member m2;


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

}
