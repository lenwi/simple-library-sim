package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.library.Book;
import model.library.Library;
import model.library.Newspaper;
import model.members.Member;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LibraryFrontDesk extends Application {

    private static String FILENAME = "members.txt";
    private static String SPLIT_CHAR = "\t";
    private static String SPLIT_SPACE = "\\s+";
    private static final int BOX_WIDTH = 310;

    private Library library;
    private Book b1;
    private Book b2;
    private Book b3;
    private Book b4;
    private Book b5;
    private Stage window;
    private Scene s1;
    private Scene s2;
    private Scene s3;
    private Scene s5;
    private Scene s6;
    private Scene s7;
    private Scene s8;
    private Scene s9;
    private Scene s10;
    private Scene scene;
    private Member member;
    private Newspaper newspaper;
    private TextField userField2;
    private Label notRegistered;
    private TextField userField3;
    private TextField titleField;
    private TextField userField4;
    private TextField titleField2;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        window.setTitle("LibraryFrontDesk");

        GridPane grid = getGridPane();

        Label welcomeText = getLabel("Welcome to the library, what would you like to do today?", 0);

        VBox vbox = new VBox(10);

        Button b1 = makeButton1();
        Button b2 = makeButton2();
        Button b3 = makeButton3();
        Button b5 = makeButton5();
        Button b6 = makeButton6();

        vbox.getChildren().addAll(b1, b2, b3, b5, b6);
        GridPane.setConstraints(vbox, 9, 5);

        grid.getChildren().addAll(welcomeText, vbox);

        scene = new Scene(grid, 550, 300);
        window.setScene(scene);

        window.show();
    }

    private Label getLabel(String s, int i) {
        Label welcomeText = new Label(s);
        welcomeText.setStyle("-fx-text-fill: #008B8B;");
        GridPane.setConstraints(welcomeText, 9, i);
        return welcomeText;
    }

    private Label getLabel(String s) {
        Label membersList = new Label(s);
        membersList.setStyle("-fx-text-fill: #008B8B;");
        membersList.setMinWidth(200);
        GridPane.setConstraints(membersList, 9, 2);
        return membersList;
    }

    private GridPane getGridPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 10, 8, 10));
        grid.setVgap(5);
        grid.setHgap(12);
        grid.setStyle("-fx-background-color: #F5DEB3;");
        return grid;
    }

    private Button makeButton1() {
        Button b1 = getButton("Sign up");

        b1.setOnAction(e -> window.setScene(s1));

        GridPane grid1 = getGridPane();

        Label userText = getLabel("Enter a username", 1);

        TextField userField = getTextField();

        Label ageText = getLabel("Enter your age", 5);

        TextField ageField = getAgeField(6);

        Button enter = getButton();
        enter.setOnAction(e -> {
            signUpLambda(grid1, userField, ageField);
        });

        Button home = getHomeButton("Home", 14, 0);

        Button clear = getButton(userField, ageField);

        grid1.getChildren().addAll(userText, userField, ageText, ageField, enter, home, clear);
        s1 = new Scene(grid1, 550, 300);

        return b1;
    }

    private void signUpLambda(GridPane grid1, TextField userField, TextField ageField) {
        if (isInt(ageField.getText())) {
            signUpMember(userField.getText(), ageField.getText());
            Label succ = getLabel(userField.getText() + " has been signed up", 9);
            grid1.getChildren().add(succ);
        } else {
            Label err = getLabel("Error: wrong age input", 10);
            grid1.getChildren().add(err);
        }
    }

    private Button getButton() {
        Button enter = new Button("Enter");
        enter.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");
        GridPane.setConstraints(enter, 9, 7);
        return enter;
    }

    private Button getButton(TextField userField, TextField ageField) {
        Button clear = new Button("Clear");
        clear.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");
        GridPane.setConstraints(clear, 9, 8);
        clear.setOnAction(e -> {
            userField.setText("");
            ageField.setText("");
        });
        return clear;
    }

    private Button getButton(String s) {
        Button b1 = new Button(s);
        b1.setMinWidth(BOX_WIDTH);
        b1.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");
        return b1;
    }

    private TextField getAgeField(int i) {
        TextField ageField = new TextField();
        ageField.setMinWidth(BOX_WIDTH);
        GridPane.setConstraints(ageField, 9, i);
        return ageField;
    }

    private void signUpMember(String nameField, String ageField) {
        member = new Member(nameField);
        member.sortAge(Integer.parseInt(ageField));
        library.addMember(member);
        library.addMemberHash(nameField);
        try {
            library.writeFile("members.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Button getHomeButton(String h, int i, int i2) {
        Button home = new Button(h);
        home.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");
        GridPane.setConstraints(home, i, i2);
        home.setOnAction(e -> window.setScene(scene));
        return home;
    }

    private TextField getTextField() {
        TextField userField = getAgeField(2);
        return userField;
    }

    private Button makeButton2() throws IOException {
        Button b2 = getButton("View current members");

        b2.setOnAction(e -> window.setScene(s2));

        GridPane grid2 = getGridPane();

        Button home2 = getHomeButton("Home", 22, 0);

        Label membersList = getLabel(loadMembers());

        Button refresh = getRefreshButton("Refresh", 22, 1);

        refresh.setOnAction(e -> {
            try {
                membersList.setText(loadMembers());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        grid2.getChildren().addAll(home2, membersList, refresh);
        s2 = new Scene(grid2, 550, 300);

        return b2;
    }

    private Button makeButton3() {
        Button b3 = getButton("Browse books");

        b3.setOnAction(e -> window.setScene(s3));

        GridPane grid3 = getGridPane();

        Button home3 = getHomeButton("Home", 14, 0);

        Label booksList = new Label(loadBooks());
        booksList.setStyle("-fx-text-fill: #008B8B;");
        booksList.setMinWidth(200);
        GridPane.setConstraints(booksList,9, 2);

        Button refresh2 = getRefreshButton("Refresh", 14, 1);

        refresh2.setOnAction(e -> booksList.setText(loadBooks()));

        grid3.getChildren().addAll(home3, booksList, refresh2);
        s3 = new Scene(grid3, 550, 300);

        return b3;
    }

    private Button getRefreshButton(String refresh, int i, int i2) {
        Button refresh2 = new Button(refresh);
        refresh2.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");
        GridPane.setConstraints(refresh2, i, i2);
        return refresh2;
    }

    private Button makeButton5() {
        Button b5 = getButton("Borrow a book");

        b5.setOnAction(e -> window.setScene(s5));

        GridPane grid5 = getGridPane();

        Label userText2 = getLabel("Enter your username", 1);

        userField2 = getTextField();

        Label titleText = getLabel("Enter the title of the book you want to borrow", 5);

        getTitleField();

        Button enter2 = getButton();
        enter2.setOnAction(e -> {
            borrowBookLambda(grid5);
        });

        Button home5 = getHomeButton("Home", 14, 0);

        grid5.getChildren().addAll(userText2, userField2, enter2, home5, titleField, titleText);
        s5 = new Scene(grid5, 550, 300);

        return b5;
    }

    private void borrowBookLambda(GridPane grid5) {
        String user = userField2.getText();
        if (library.containsMember(user)) {
            borrowBook();
            Label enj = getLabel("Enjoy!", 8);
            grid5.getChildren().add(enj);
        } else {
            notRegistered = getLabel("You are not registered, please sign up!", 9);
            grid5.getChildren().add(notRegistered);
        }
    }

    private void getTitleField() {
        titleField = new TextField();
        titleField.setMinWidth(BOX_WIDTH);
        GridPane.setConstraints(titleField, 9, 6);
    }

    private Button makeButton6() {
        Button b6 = getButton("Return a book");

        b6.setOnAction(e -> window.setScene(s6));

        GridPane grid6 = getGridPane();

        Label userText3 = getLabel("Enter your username", 1);

        userField3 = getTextField();

        Label titleText = getLabel("Enter the title of the book you want to return", 5);

        getTitleField2();

        Button enter3 = getHomeButton("Enter", 9, 7);
        enter3.setOnAction(e -> {
            returnBookLambda(grid6);
        });

        Button home6 = getHomeButton("Home", 14, 0);

        grid6.getChildren().addAll(userText3, userField3, titleText, titleField2, enter3, home6);
        s6 = new Scene(grid6, 550, 300);

        return b6;
    }

    private void returnBookLambda(GridPane grid6) {
        String user = userField3.getText();
        ArrayList<Book> bb = library.getBookList(user);
        if (library.containsMember(user)) {
            returnBook(userField3.getText(), titleField2.getText());
            Label nice = getLabel("Thank you, have a nice day!", 8);
            Label borrowed = getLabel("Currently borrowed books: \n" + library.displayBorrowedBooks(bb), 10);
            grid6.getChildren().addAll(nice, borrowed);
        } else {
            notRegistered = getLabel("Incorrect user", 9);
            grid6.getChildren().add(notRegistered);
        }
    }

    private void getTitleField2() {
        titleField2 = new TextField();
        titleField2.setMinWidth(BOX_WIDTH);
        GridPane.setConstraints(titleField2, 9, 6);
    }

    private boolean isInt(String ageinput) {
        try {
            Integer.parseInt(ageinput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public LibraryFrontDesk() throws IOException {
        this.library = new Library();
        loadMembers();
        loadBooks();
    }

    // getters
    public Library getLibrary() {
        return library;
    }

    // MODIFIES: this
    // EFFECTS: loads members of the library
    private String loadMembers() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));

        String result = "";

        for (String line: lines) {
            String[] parts = split(line);

            String name = parts[0];
            String agegroup = parts[1];

            result += "\n" + name + ": " + agegroup;
        }
        return result;
    }

    public static String[] split(String line) {
        return line.split(SPLIT_CHAR);
    }

    public static String[] smallSplit(String line) {
        return line.split(SPLIT_SPACE);
    }

    // MODIFIES: this
    // EFFECTS: loads books in the library
    private String loadBooks() {

        String res = "";

        for (Book b: library.getBooks()) {
            res += "\n" + b.getTitle() + "- " + b.getCategory();
        }
        return res;
    }

    private void borrowBook() {
        String user = userField2.getText();
        ArrayList<Book> bb = library.getBookList(user);
        String book = titleField.getText();
        library.borrow(user, library.findBook(book));
        GridPane grid7 = getGridPane();

        Label userText4 = getLabel("Enter the title of the book you want to borrow", 1);

        userField4 = getTextField();

        Button enter4 = getButton();
        enter4.setOnAction(e -> {
            library.borrow(user, library.findBook(book));
            Label enj = getLabel("Enjoy!", 8);
            grid7.getChildren().add(enj);
        });

        Button home7 = getHomeButton("Home", 14, 0);

        grid7.getChildren().addAll(userText4, userField4, enter4, home7);
        s7 = new Scene(grid7, 550, 300);
    }

    private void returnBook(String usern, String titlen) {
        String user = usern;
        ArrayList<Book> bb = library.getBookList(user);
        String book = titlen;
        library.returnBook(user, library.findBorrowedBook(user, book));
    }

}
