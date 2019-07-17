package model.library;

import java.io.IOException;

public interface Loadable {

    // REQUIRES: a valid file
    // MODIFIES: this/file
    // EFFECTS: reads members from a text file
    void readMembers() throws IOException;
}
