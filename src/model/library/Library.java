package model.library;

import model.members.Member;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ui.LibraryFrontDesk.split;

public class Library implements Loadable, Saveable {

    static String FILENAME = "members.txt";
    static String SPLIT_CHAR = "\t";

    private List<Member> members;
    private List<Book> books;

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
    }

    @Override
    // REQUIRES: a valid member
    // MODIFIES: this/file
    // EFFECTS: writes in member info to a text file
    public void writeMembers() throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(FILENAME, true));
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
