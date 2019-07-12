import model.members.AgeGroup;
import model.members.Member;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MemberTest {
    Member testMember;

    @Before
    public void setup() {
        testMember = new Member("name");
    }

    @Test
    public void testSortAge() {
        testMember.sortAge(0);
        assertTrue(testMember.getAgeGroup() == AgeGroup.CHILD);
        testMember.sortAge(5);
        assertTrue(testMember.getAgeGroup() == AgeGroup.CHILD);
        testMember.sortAge(12);
        assertTrue(testMember.getAgeGroup() == AgeGroup.CHILD);
        assertFalse(testMember.getAgeGroup() == AgeGroup.ADOLESCENCE);
        assertFalse(testMember.getAgeGroup() == AgeGroup.ADULT);
        testMember.sortAge(13);
        assertTrue(testMember.getAgeGroup() == AgeGroup.ADOLESCENCE);
        testMember.sortAge(15);
        assertTrue(testMember.getAgeGroup() == AgeGroup.ADOLESCENCE);
        testMember.sortAge(18);
        assertTrue(testMember.getAgeGroup() == AgeGroup.ADOLESCENCE);
        assertFalse(testMember.getAgeGroup() == AgeGroup.CHILD);
        assertFalse(testMember.getAgeGroup() == AgeGroup.ADULT);
        testMember.sortAge(19);
        assertTrue(testMember.getAgeGroup() == AgeGroup.ADULT);
        testMember.sortAge(50);
        assertEquals(testMember.getAgeGroup(), AgeGroup.ADULT);
        assertFalse(testMember.getAgeGroup() == AgeGroup.ADOLESCENCE);
        assertFalse(testMember.getAgeGroup() == AgeGroup.CHILD);
    }
}
