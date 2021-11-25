package com.triviamaze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.sqlite.SQLiteDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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
    @FXML
    private TextField typeAnswerTextField;
    @FXML
    private Label shortAnswerLabel;


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
    private void generateQuestion() {
        trivia.chooseQuestion();

        setAnswerButtonsDisabled(false);
        resultLabel.setText("");

        String ansA = trivia.getAnswerA();
        String ansB = trivia.getAnswerB();
        String ansC = trivia.getAnswerC();
        String ansD = trivia.getAnswerD();
        myAnswer = trivia.getAnswer();

        if (ansA.equals("-Fill-")) { // for short answer questions
            setAnswerButtonsDisabled(true);
            typeAnswerTextField.setDisable(false);
            shortAnswerLabel.setVisible(true);
            shortAnswerLabel.setDisable(false);
            labelA.setText("");
            labelB.setText("");
            labelC.setText("");
            labelD.setText("");
        } else {
            labelA.setText(ansA);
            labelB.setText(ansB);
            labelC.setText(ansC);
            labelD.setText(ansD);

            if (labelC.getText().equals(" ") && labelD.getText().equals(" ")) {  //for true/false questions
                buttonC.setDisable(true);
                buttonD.setDisable(true);
            }
        }
        questionLabel.setText(trivia.getQuestion());

    }

    @FXML
    private void answerA() {
        checkAnswer("A");
    }

    @FXML
    private void answerB() {
        checkAnswer("B");
    }

    @FXML
    private void answerC() {
        checkAnswer("C");
    }

    @FXML
    private void answerD() {
        checkAnswer("D");
    }

    @FXML
    private void shortAnswer() {
        String answer = typeAnswerTextField.getText().toLowerCase();
        checkShortAnswer(answer);
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

    private void checkShortAnswer(String theGuess) {
        checkAnswer(theGuess);
        typeAnswerTextField.setDisable(true);
        typeAnswerTextField.setText("");
        shortAnswerLabel.setVisible(false);
        shortAnswerLabel.setDisable(true);
    }

    @FXML
    private void setAnswerButtonsDisabled(boolean theState) {
        buttonA.setDisable(theState);
        buttonB.setDisable(theState);
        buttonC.setDisable(theState);
        buttonD.setDisable(theState);
    }

    public boolean getMyResult() {
        return myResult;
    }

}
