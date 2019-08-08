package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    Stage window;
    Scene scene1;
    Scene scene2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("LibraryFrontDesk");

        HBox top = new HBox();
        Button b1 = new Button("file");
        Button b2 = new Button("fil");
        Button b3 = new Button("fi");
        top.getChildren().addAll(b1, b2, b3);

        VBox left = new VBox();
        Button b4 = new Button("s");
        Button b5 = new Button("fdl");
        Button b6 = new Button("di");
        top.getChildren().addAll(b4, b5, b6);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(top);
        borderPane.setLeft(left);

        Scene scene = new Scene(borderPane, 700, 250);
        window.setScene(scene);
        window.show();
    }
}
