package model.library;

import model.members.AgeGroup;
import model.members.Member;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ui.LibraryFrontDesk.split;

public class Library {

    static String FILENAME = "members.txt";
    static String SPLIT_CHAR = "\t";

    private List<Member> members;
    private List<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
        Member m1 = new Member("Bob");
        m1.setAgeGroup(AgeGroup.ADULT);
        Member m2 = new Member("Jill");
        m2.setAgeGroup(AgeGroup.ADOLESCENCE);
        addMember(m1);
        addMember(m2);
    }

    public void writeMembers() throws IOException {

        PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
        for (Member m: members) {
            System.out.println("Writing member details: " + m.getName());
            writer.println(m.getName() + SPLIT_CHAR + m.getAgeGroup());
        }
        writer.close();
    }

    public void readMembers() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        for (String line: lines) {
            String[] parts = split(line);

            String name = parts[0];
            String agegroup = parts[1];

            System.out.println(name + ": " + agegroup);
        }
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
