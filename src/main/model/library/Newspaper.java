package model.library;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static model.library.Library.SPLIT_CHAR;
import static ui.LibraryFrontDesk.smallSplit;

public class Newspaper extends Readings implements Saveable,Loadable {
    private int quantity;

    public Newspaper(String title, String issueNum, int quantity) {
        super(title, issueNum);
        this.quantity = quantity;
    }

    // EFFECTS: returns this newspaper's issue #
    @Override
    public String getCategory() {
        return "Issue # " + category;
    }

    // EFFECTS: returns the amount of newspapers available to take
    public int getQuantity() {
        return quantity;
    }

    // EFFECTS: returns the issue number
    public String getIssueNum() {
        return category;
    }

    // MODIFIES: this
    // EFFECTS: sets this newspaper's category to given category
    @Override
    public void setCategory(String category) {
        this.category = category;
    }

    // MODIFIES: this
    // EFFECTS: sets this newspaper's issueNum to given issueNum
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // MODIFIES: this
    // EFFECTS: take a newspaper and decrement the quantity by 1
    public void takeNewspaper() {
        if (quantity < 1) {
            setCategory(String.valueOf(Integer.parseInt(category) + 1));
            setQuantity(10);
        }
        this.quantity = quantity - 1;
    }

    // REQUIRES: a valid string
    // MODIFIES: this/file
    // EFFECTS: writes in issue number info to a text file
    @Override
    public void writeFile(String file) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(file, false));
        System.out.println("Saving newspaper details");
        writer.println(getCategory() + SPLIT_CHAR + getQuantity());

        writer.flush();
        writer.close();
    }

    @Override
    // REQUIRES: a valid file
    // MODIFIES: this/file
    // EFFECTS: reads issue number from a text file
    public void readFile(String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));

        for (String line: lines) {
            String[] parts = smallSplit(line);

            String issue = parts[0];
            String hash = parts[1];
            String num = parts[2];
            String quantity = parts[3];
            setCategory(num);
            setQuantity(Integer.parseInt(quantity));

            System.out.println("Here is " + getTitle() + " " + issue);
            System.out.println("\n There are " + quantity + " left of "
                    + issue + " " + hash + " " + num + ".");
        }
    }
}
