package model.library;

import model.members.Member;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Member> members;

    public Library() {
        members = new ArrayList<>();
    }

    // getters
    public List<Member> getMembers() { return members; }


    // REQUIRES: m is not null
    // MODIFIES: this
    // EFFECTS adds member m to the library
    public void addMember(Member m) {
        members.add(m);
    }
}
