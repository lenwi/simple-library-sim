package model.library;

public class Book extends Readings {

    public Book(String title, String genre) {
        super(title, genre);
    }

    // EFFECTS: returns this book's category
    @Override
    public String getCategory() {
        return category;
    }

    // MODIFIES: this
    // EFFECTS: sets this book's category to given category
    @Override
    public void setCategory(String category) {
        this.category = category;
    }
}
