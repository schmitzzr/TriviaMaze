package com.triviamaze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * GameScene controller for the MainMenu JavaFX Application.
 */
public class GameSceneController {

    @FXML
    private Label resultLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Label labelA;
    @FXML
    private Label labelB;
    @FXML
    private Label labelC;
    @FXML
    private Label labelD;
    @FXML
    private Button buttonA;
    @FXML
    private Button buttonB;
    @FXML
    private Button buttonC;
    @FXML
    private Button buttonD;


    private final Trivia trivia = new Trivia("jdbc:sqlite:questions.db", "multipleChoice");

    private String myAnswer;
    private boolean myResult = true;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private void exitButtonClicked(final ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void returnToMainMenu(final ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/Styles.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void generateQuestion(final ActionEvent event) {
        trivia.chooseQuestion();

        setAnswerButtonsDisabled(false);
        resultLabel.setText("");

        labelA.setText(trivia.getAnswerA());
        labelB.setText(trivia.getAnswerB());
        labelC.setText(trivia.getAnswerC());
        labelD.setText(trivia.getAnswerD());
        questionLabel.setText(trivia.getQuestion());
        myAnswer = trivia.getAnswer();

    }

    @FXML
    private void answerA(final ActionEvent event) {
        checkAnswer("A");
    }

    @FXML
    private void answerB(final ActionEvent event) {
        checkAnswer("B");
    }

    @FXML
    private void answerC(final ActionEvent event) {
        checkAnswer("C");
    }

    @FXML
    private void answerD(final ActionEvent event) {
        checkAnswer("D");
    }

    @FXML
    private void setAnswerButtonsDisabled(boolean theState) {
        buttonA.setDisable(theState);
        buttonB.setDisable(theState);
        buttonC.setDisable(theState);
        buttonD.setDisable(theState);
    }

    private void checkAnswer(String theGuess) {
        if (theGuess.equals(myAnswer)) {
            resultLabel.setText("Correct!\nYou may continue.");
            myResult = true;
        } else {
            resultLabel.setText("Incorrect!\nThis bridge has broken.");
            myResult = false;
        }
        setAnswerButtonsDisabled(true);
    }

}
