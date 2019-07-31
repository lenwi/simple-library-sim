package model.library;

import java.io.File;
import java.io.IOException;

public interface Saveable {

    // REQUIRES: a valid member
    // MODIFIES: this/file
    // EFFECTS: writes in member info to a text file
    void writeFile(String file) throws IOException;
}
