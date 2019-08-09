package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

    private static final int BOX_WIDTH = 310;

    private Stage window;
    private Scene s1;
    private Scene s2;
    private Scene s3;
    private Scene s4;
    private Scene s5;
    private Scene s6;
    private Scene s7;
    private Scene s8;
    private Scene s9;
    private Scene s10;
    private Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("LibraryFrontDesk");

        GridPane grid = getGridPane();

        Label welcomeText = getLabel("Welcome to the library, what would you like to do today?", 0);

        VBox vbox = new VBox(10);

        Button b1 = makeButton1();
        Button b2 = makeButton2();
        Button b3 = makeButton3();
        Button b4 = makeButton4();
        Button b5 = makeButton5();
        Button b6 = makeButton6();

        vbox.getChildren().addAll(b1, b2, b3, b4, b5, b6);
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

    private GridPane getGridPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 10, 8, 10));
        grid.setVgap(5);
        grid.setHgap(12);
        grid.setStyle("-fx-background-color: #F5DEB3;");
        return grid;
    }

    private Button makeButton1() {
        Button b1 = new Button("Sign up");
        b1.setMinWidth(BOX_WIDTH);
        b1.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");

        b1.setOnAction(e -> window.setScene(s1));

        GridPane grid1 = getGridPane();

        Label userText = getLabel("Enter a username", 1);

        TextField userField = getTextField();

        Label ageText = getLabel("Enter your age", 5);

        TextField ageField = new TextField();
        ageField.setMinWidth(BOX_WIDTH);
        GridPane.setConstraints(ageField, 9, 6);

        Button enter = getButton("Enter", 9, 7);

        Button home = getButton("Home", 14, 0);

        grid1.getChildren().addAll(userText, userField, ageText, ageField, enter, home);
        s1 = new Scene(grid1, 550, 300);

        return b1;
    }

    private Button getButton(String enter2, int i, int i2) {
        Button enter = new Button(enter2);
        enter.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");
        GridPane.setConstraints(enter, i, i2);
        enter.setOnAction(e -> window.setScene(scene));
        return enter;
    }

    private TextField getTextField() {
        TextField userField = new TextField();
        userField.setMinWidth(BOX_WIDTH);
        GridPane.setConstraints(userField, 9, 2);
        return userField;
    }

    private Button makeButton2() {
        Button b2 = new Button("View current members");
        b2.setMinWidth(BOX_WIDTH);
        b2.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");

        b2.setOnAction(e -> window.setScene(s2));

        GridPane grid2 = getGridPane();

        Button home2 = getButton("Home", 14, 0);

        grid2.getChildren().addAll(home2);
        s2 = new Scene(grid2, 550, 300);

        return b2;
    }

    private Button makeButton3() {
        Button b3 = new Button("Browse books");
        b3.setMinWidth(BOX_WIDTH);
        b3.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");

        b3.setOnAction(e -> window.setScene(s3));

        GridPane grid3 = getGridPane();

        Button home3 = getButton("Home", 14, 0);

        grid3.getChildren().addAll(home3);
        s3 = new Scene(grid3, 550, 300);

        return b3;
    }

    private Button makeButton4() {
        Button b4 = new Button("Read news");
        b4.setMinWidth(BOX_WIDTH);
        b4.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");

        b4.setOnAction(e -> window.setScene(s4));

        GridPane grid4 = getGridPane();

        Button home4 = getButton("Home", 14, 0);

        grid4.getChildren().addAll(home4);
        s4 = new Scene(grid4, 550, 300);

        return b4;
    }

    private Button makeButton5() {
        Button b5 = new Button("Borrow a book");
        b5.setMinWidth(BOX_WIDTH);
        b5.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");

        b5.setOnAction(e -> window.setScene(s5));

        GridPane grid5 = getGridPane();

        Label userText2 = getLabel("Enter your username", 1);

        TextField userField2 = getTextField();

        Button enter2 = getButton("Enter", 9, 3);

        Button home5 = getButton("Home", 14, 0);

        grid5.getChildren().addAll(userText2, userField2, enter2, home5);
        s5 = new Scene(grid5, 550, 300);

        return b5;
    }

    private Button makeButton6() {
        Button b6 = new Button("Return a book");
        b6.setMinWidth(BOX_WIDTH);
        b6.setStyle("-fx-background-color: #D2B48C;" + "-fx-text-fill: #008B8B;");

        b6.setOnAction(e -> window.setScene(s6));

        GridPane grid6 = getGridPane();

        Label userText3 = getLabel("Enter your username", 1);

        TextField userField3 = getTextField();

        Button enter3 = getButton("Enter", 9, 3);

        Button home6 = getButton("Home", 14, 0);

        grid6.getChildren().addAll(userText3, userField3, enter3, home6);
        s6 = new Scene(grid6, 550, 300);

        return b6;
    }
}
