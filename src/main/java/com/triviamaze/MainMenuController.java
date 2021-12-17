package com.triviamaze;

import com.triviamaze.maze.Maze;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * MainMenu controller for the MainMenu JavaFX Application.
 */
public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Exits the program.
     * @param event exiting the program
     */
    @FXML
    private void exitButtonClicked(final ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    /**
     * Changes scenes to DifficultyScene, where you select the difficulty of a new game.
     * @param event clicking the "Start a new game" button
     * @throws IOException in case files are not found
     */
    public void setDifficulty(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DifficultyScene.fxml"));
        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/DifficultyScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes to the game scene, loading the previous progress.
     * @param event clicking the "Load previous game" button
     * @throws IOException in case files are not found
     */
    public void loadGame(final ActionEvent event) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/GameScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Maze maze;
        try {
            InputStream file = new FileInputStream("status");
            ObjectInputStream out = new ObjectInputStream(file);
            maze = (Maze) out.readObject();
            file.close();
            GameSceneController.setMyMaze(maze);
            System.out.print("Hello Hello");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Changes to the settings scene.
     * @param theEvent clicking the settings button
     * @throws IOException in case files are not found
     */
    public void settingsButtonClicked(final ActionEvent theEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsScene.fxml")));
        stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/SettingsScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes to the help scene.
     * @param theEvent clicking the help button
     * @throws IOException in case files are not found
     */
    public void helpButtonClicked(final ActionEvent theEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpScene.fxml")));
        stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/HelpScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Changes to the scoreboard scene.
     * @param theEvent clicking the scoreboard button
     * @throws IOException in case files are not found
     */
    public void scoreboardButtonClicked(final ActionEvent theEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ScoreboardScene.fxml")));
        stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/ScoreboardScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}

