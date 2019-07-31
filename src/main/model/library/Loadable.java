package model.library;

import java.io.File;
import java.io.IOException;

public interface Loadable {

    // REQUIRES: a valid file
    // MODIFIES: this/file
    // EFFECTS: reads members from a text file
    void readFile(String file) throws IOException;
}
