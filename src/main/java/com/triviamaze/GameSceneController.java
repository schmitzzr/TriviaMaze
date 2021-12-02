package com.triviamaze;

import com.triviamaze.maze.Direction;
import com.triviamaze.maze.Maze;
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

import java.io.IOException;
import java.util.Objects;

/**
 * GameScene controller for the MainMenu JavaFX Application.
 */
public class GameSceneController {



    @FXML
    private Label resultLabel, questionLabel, shortAnswerLabel;
    @FXML
    private Label labelA, labelB, labelC, labelD;
    @FXML
    private Button buttonA, buttonB, buttonC, buttonD;
    @FXML
    private TextField typeAnswerTextField;

    @FXML
    private Button EastBridge,NorthBridge,WestBridge,SouthBridge;


    private final Trivia myTrivia = new Trivia("jdbc:sqlite:questions.db", "multipleChoice");
    private final Maze myMaze = new Maze(4,4,0,0,3,3);


    private String myAnswer;
    private boolean myResult = true;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public enum State {
            OPEN("open"),
                    LOCKED("locked"),
                    UNLOCKED("unlocked"),
                    QUESTION("question");

    private final String label;
    State(String label) {
        this.label = label;
    }}

    //For initialization of bridges.
    @FXML
    public void setBridges(boolean theNorth,boolean theEast,boolean theWest, boolean theSouth) {
            NorthBridge.setVisible(theNorth);
            EastBridge.setVisible(theEast);
            WestBridge.setVisible(theWest);
            SouthBridge.setVisible(theSouth);
    }

    @FXML
    public void unlockBridge() {
        if(NorthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            NorthBridge.setText(String.valueOf(State.UNLOCKED));
        }
        if(EastBridge.getText().equals(String.valueOf(State.QUESTION))) {
            EastBridge.setText(String.valueOf(State.UNLOCKED));
        }
        if(WestBridge.getText().equals(String.valueOf(State.QUESTION))) {
            WestBridge.setText(String.valueOf(State.UNLOCKED));
        }
        if(SouthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            SouthBridge.setText(String.valueOf(State.UNLOCKED));
        }
    }

    @FXML
    public void lockBridge() {
        if(NorthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            NorthBridge.setText(String.valueOf(State.LOCKED));
            NorthBridge.setVisible(false);
        }
        if(EastBridge.getText().equals(String.valueOf(State.QUESTION))) {
            EastBridge.setText(String.valueOf(State.LOCKED));
            EastBridge.setVisible(false);
        }
        if(WestBridge.getText().equals(String.valueOf(State.QUESTION))) {
            WestBridge.setText(String.valueOf(State.LOCKED));
            WestBridge.setVisible(false);
        }
        if(SouthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            SouthBridge.setText(String.valueOf(State.LOCKED));
            SouthBridge.setVisible(false);
        }
    }

    public void setNorthBridgeState() {

    }
    public void setEastBridgeState() {

    }
    public void setWestBridgeState() {

    }
    public void setSouthBridgeState() {

    }


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
    private void NorthClick() {
        if (NorthBridge.getText().equals(String.valueOf(State.OPEN))) {
            generateQuestion();
            NorthBridge.setText(String.valueOf(State.QUESTION));
        } else if (NorthBridge.getText().equals(String.valueOf(State.UNLOCKED))){
            myMaze.moveLocation(Direction.NORTH);
        }
    }
    @FXML
    private void EastClick() {
        if (EastBridge.getText().equals(String.valueOf(State.OPEN))) {
            generateQuestion();
            EastBridge.setText(String.valueOf(State.QUESTION));
        } else if (EastBridge.getText().equals(String.valueOf(State.UNLOCKED))){
            //next room
        }
    }
    @FXML
    private void WestClick() {
        if (WestBridge.getText().equals(String.valueOf(State.OPEN))) {
            generateQuestion();
            WestBridge.setText(String.valueOf(State.QUESTION));
        } else if (WestBridge.getText().equals(String.valueOf(State.UNLOCKED))){
            //next room
        }
    }
    @FXML
    private void SouthClick() {
        if (SouthBridge.getText().equals(String.valueOf(State.OPEN))) {
            generateQuestion();
            SouthBridge.setText(String.valueOf(State.QUESTION));
        } else if (SouthBridge.getText().equals(String.valueOf(State.UNLOCKED))){
            //next room
        }
    }

    @FXML
    private void generateQuestion() {
        myTrivia.chooseQuestion();

        setAnswerButtonsDisabled(false);
        resultLabel.setText("");

        String ansA = myTrivia.getAnswerA();
        String ansB = myTrivia.getAnswerB();
        String ansC = myTrivia.getAnswerC();
        String ansD = myTrivia.getAnswerD();
        myAnswer = myTrivia.getAnswer();

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
        questionLabel.setText(myTrivia.getQuestion());

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

    private void checkAnswer(final String theGuess) {
        if (theGuess.equals(myAnswer)) {
            resultLabel.setText("Correct!\nYou may continue.");
            myResult = true;
            unlockBridge();
        } else {
            resultLabel.setText("Incorrect!\nThis bridge has broken.");
            myResult = false;
            lockBridge();
        }
        setAnswerButtonsDisabled(true);
    }

    private void checkShortAnswer(final String theGuess) {
        checkAnswer(theGuess);
        typeAnswerTextField.setDisable(true);
        typeAnswerTextField.setText("");
        shortAnswerLabel.setVisible(false);
        shortAnswerLabel.setDisable(true);
    }

    @FXML
    private void setAnswerButtonsDisabled(final boolean theState) {
        buttonA.setDisable(theState);
        buttonB.setDisable(theState);
        buttonC.setDisable(theState);
        buttonD.setDisable(theState);
    }

    public boolean getMyResult() {
        return myResult;
    }

}
