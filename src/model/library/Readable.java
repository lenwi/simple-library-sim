package model.library;

public abstract class Readable {
    protected String title;
    protected String category;

    public Readable(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() { return title; }

    public abstract String getCategory();

    // MODIFIES: this
    // EFFECTS: sets this readable's title to given title
    public void setTitle(String title) { this.title = title; }

    // MODIFIES: this
    // EFFECTS: sets this readable's category to given category
    public abstract void setCategory(String category);
}
