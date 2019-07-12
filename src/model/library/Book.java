package model.library;

public class Book {

    private String title;
    private String genre;

    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    // getters

    public String getTitle() { return title; }
    public String getGenre() { return genre; }

    // MODIFIES: this
    // EFFECTS: sets this book's title to given title
    public void setTitle(String title) { this.title = title; }

    // MODIFIES: this
    // EFFECTS: sets this book's genre to given genre
    public void setGenre(String genre) { this.genre = genre; }
}
