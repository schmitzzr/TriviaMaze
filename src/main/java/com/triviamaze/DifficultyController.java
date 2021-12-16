package com.triviamaze;

import com.triviamaze.maze.Maze;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DifficultyController implements Initializable {

    @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> difficultyBox;

    private final String[] myDiffs = {"Easy (4 x 4)", "Medium (5 x 5)", "Hard (6 x 6)"};
    private String myDifficulty;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        difficultyBox.getItems().addAll(myDiffs);
        difficultyBox.setOnAction(this::getDifficulties);

    }

    private void getDifficulties(ActionEvent event) {
        myDifficulty = difficultyBox.getValue();
        startButton.setDisable(false);
    }

    public void startGame(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();

        int rows, columns;
        switch(myDifficulty) {
            case "Medium (5 x 5)" -> {rows = 5; columns = 5;}
            case "Hard (6 x 6)" -> {rows = 6; columns = 6;}
            default -> {rows = 4; columns = 4;}
        }
        GameSceneController.setMyMaze(new Maze(rows,columns,0,0,rows - 1,columns - 1));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/GameScene.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
