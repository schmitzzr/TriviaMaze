package com.triviamaze;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {
    /**
     * Main method.
     * @param args
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
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setWidth(1300);
        primaryStage.setHeight(730);

        primaryStage.show();
    }
}