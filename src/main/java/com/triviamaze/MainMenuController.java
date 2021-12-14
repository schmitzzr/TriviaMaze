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

    SettingsSceneController mySettings = new SettingsSceneController();

    @FXML
    private void exitButtonClicked(final ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }





    public void startGame(final ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/GameScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        mySettings.music();

//        GameSceneController gameSceneController = new GameSceneController();
//        gameSceneController.setMyMaze(new Maze(4,4,0,0,3,3));
    }



    public void loadGame(final ActionEvent event) throws IOException {

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/GameScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        Maze maze=null;
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

    public void settingsButtonClicked(final ActionEvent theEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsScene.fxml")));
        stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/SettingsScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void helpButtonClicked(final ActionEvent theEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpScene.fxml")));
        stage = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/HelpScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}

