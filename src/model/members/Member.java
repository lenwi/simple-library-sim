package model.members;

import static model.members.AgeGroup.ADOLESCENCE;
import static model.members.AgeGroup.ADULT;
import static model.members.AgeGroup.CHILD;

public class Member {

    private String name;
    private AgeGroup ageGroup;

    public Member(String name) {
        this.name = name;
    }

    // getters
    public String getName() { return name; }
    public AgeGroup getAgeGroup() { return ageGroup; }

    // MODIFIES: this
    // EFFECTS: sets this member's name to given name
    public void setName(String name) { this.name = name; }

    // MODIFIES: this
    // EFFECTS: sets this member's age group to given age group
    public void setAgeGroup(AgeGroup ageGroup) { this.ageGroup = ageGroup; }

    // REQUIRE: positive integer
    // MODIFIES: this
    // EFFECTS: when given an int, set the agegroup as follows:
    //          0-12 CHILD, 13-18 ADOLESCENCE, 19+ ADULT
    public void sortAge(int i) {
        if (i >= 0 && i <= 12) {
            setAgeGroup(CHILD);
        } else if (i >= 13 && i <= 18) {
            setAgeGroup(ADOLESCENCE);
        } else if (i >= 19) {
            setAgeGroup(ADULT);
        }
    }
}
