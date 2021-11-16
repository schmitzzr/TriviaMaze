package com.triviamaze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameScene controller for the MainMenu JavaFX Application.
 */
public class GameSceneController {

    @FXML
    private Label resultLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private void exitButtonClicked(final ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void returnToMainMenu(final ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void answerA(final ActionEvent event) {
        resultLabel.setText("A was clicked");
    }

    @FXML
    private void answerB(final ActionEvent event) {
        resultLabel.setText("B was clicked");
    }

    @FXML
    private void answerC(final ActionEvent event) {
        resultLabel.setText("C was clicked");
    }

    @FXML
    private void answerD(final ActionEvent event) {
        resultLabel.setText("D was clicked");
    }

}
