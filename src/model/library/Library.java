package model.library;

import model.members.Member;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Member> members;
    private List<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
    }

    // getters
    public List<Member> getMembers() { return members; }
    public List<Book> getBooks() { return books; }


    // REQUIRES: m is not null
    // MODIFIES: this
    // EFFECTS adds member m to the library
    public void addMember(Member m) {
        members.add(m);
    }

    // REQUIRES: b is not null
    // MODIFIES: this
    // EFFECTS: adds book b to the library
    public void addBook(Book b) {
        books.add(b);
    }
}
