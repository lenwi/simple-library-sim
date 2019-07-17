package saveload;

import model.members.AgeGroup;
import model.members.Member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TSVWriter {
    static String FILENAME = "members.txt";
    static String SPLIT_CHAR = "\t";

    public static void main(String[] args) throws IOException {

        Member m1 = new Member("Bob");
        m1.setAgeGroup(AgeGroup.ADULT);
        Member m2 = new Member("Jill");
        m2.setAgeGroup(AgeGroup.ADOLESCENCE);
        List<Member> members = new ArrayList<>();
        members.add(m1);
        members.add(m2);

        PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
        for (Member m: members) {
            System.out.println("Writing member details: " + m.getName());
            writer.println(m.getName() + SPLIT_CHAR + m.getAgeGroup());
        }
        writer.close();
    }
}
