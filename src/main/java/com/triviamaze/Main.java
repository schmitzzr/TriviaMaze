package com.triviamaze;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    /**
     * Main method.
     * @param args arguments.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     *Start method for the JavaFX Stage
     * @param primaryStage stage for the application.
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    public void start(final Stage primaryStage) throws IOException {
        primaryStage.setTitle("Trivia Maze");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/Styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(730);

        primaryStage.show();
    }






}