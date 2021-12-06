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
import java.io.Serializable;
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
    private TextField typeAnswerTextField, cheatField;

    @FXML
    private Button EastBridge = new Button(),NorthBridge = new Button(),WestBridge = new Button(),SouthBridge = new Button();

    /** Initializes the trivia database to be used */
    private final Trivia myTrivia = new Trivia("jdbc:sqlite:questions.db", "multipleChoice");

    /** initializes the maze to be used by default */
    private Maze myMaze = new Maze(4,4,0,0,3,3);

    private String myAnswer;
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

    @FXML
    public void setMyMaze(final Maze theMaze) {
        myMaze = theMaze;
        System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        setBridges();
    }

    //For initialization of bridges.
    @FXML
    public void setBridges() {
        NorthBridge.setVisible(myMaze.getMyCurrentRoom().getMyBridgeN().getOpenStatus());
        NorthBridge.setDisable(!myMaze.getMyCurrentRoom().getMyBridgeN().getOpenStatus());

        EastBridge.setVisible(myMaze.getMyCurrentRoom().getMyBridgeE().getOpenStatus());
        EastBridge.setDisable(!myMaze.getMyCurrentRoom().getMyBridgeE().getOpenStatus());

        WestBridge.setVisible(myMaze.getMyCurrentRoom().getMyBridgeW().getOpenStatus());
        WestBridge.setDisable(!myMaze.getMyCurrentRoom().getMyBridgeW().getOpenStatus());

        SouthBridge.setVisible(myMaze.getMyCurrentRoom().getMyBridgeS().getOpenStatus());
        SouthBridge.setDisable(!myMaze.getMyCurrentRoom().getMyBridgeS().getOpenStatus());

        if (myMaze.isAtEnd()) {
            System.out.println("You won!");
        }
    }

    @FXML
    public void unlockBridge() {

        if(NorthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            NorthBridge.setText(String.valueOf(State.UNLOCKED));
            myMaze.breakOrSolveBridge(Direction.NORTH, true);
        }
        if(EastBridge.getText().equals(String.valueOf(State.QUESTION))) {
            EastBridge.setText(String.valueOf(State.UNLOCKED));
            myMaze.breakOrSolveBridge(Direction.EAST, true);
        }
        if(WestBridge.getText().equals(String.valueOf(State.QUESTION))) {
            WestBridge.setText(String.valueOf(State.UNLOCKED));
            myMaze.breakOrSolveBridge(Direction.WEST, true);
        }
        if(SouthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            SouthBridge.setText(String.valueOf(State.UNLOCKED));
            myMaze.breakOrSolveBridge(Direction.SOUTH, true);
        }
    }


    @FXML
    public void lockBridge() {
        if(NorthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            NorthBridge.setText(String.valueOf(State.LOCKED));
            myMaze.breakOrSolveBridge(Direction.NORTH, false);
            NorthBridge.setVisible(false);
        }
        if(EastBridge.getText().equals(String.valueOf(State.QUESTION))) {
            EastBridge.setText(String.valueOf(State.LOCKED));
            myMaze.breakOrSolveBridge(Direction.EAST, false);
            EastBridge.setVisible(false);
        }
        if(WestBridge.getText().equals(String.valueOf(State.QUESTION))) {
            WestBridge.setText(String.valueOf(State.LOCKED));
            myMaze.breakOrSolveBridge(Direction.WEST, false);
            WestBridge.setVisible(false);
        }
        if(SouthBridge.getText().equals(String.valueOf(State.QUESTION))) {
            SouthBridge.setText(String.valueOf(State.LOCKED));
            myMaze.breakOrSolveBridge(Direction.SOUTH, false);
            SouthBridge.setVisible(false);
        }
    }

    /**
     * Sets the bridges to be unusable (false) or usable (true).
     * This is for when the player gets asked the trivia question.
     * Otherwise, they can continue to click the bridges and cycle through the trivia questions.
     * Setting this to false re-enables those bridges.
     * @param theState the usability of the bridges (false if unusable, true otherwise)
     */
    private void pauseBridges(final boolean theState) {
        if (myMaze.getMyCurrentRoom().getMyBridgeN().getOpenStatus()) {
            NorthBridge.setDisable(theState);
        }
        if (myMaze.getMyCurrentRoom().getMyBridgeE().getOpenStatus()) {
            EastBridge.setDisable(theState);
        }
        if (myMaze.getMyCurrentRoom().getMyBridgeW().getOpenStatus()) {
            WestBridge.setDisable(theState);
        }
        if (myMaze.getMyCurrentRoom().getMyBridgeS().getOpenStatus()) {
            SouthBridge.setDisable(theState);
        }
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
        if (!myMaze.getMyCurrentRoom().getMyBridgeN().getQuestionStatus()) {
            generateQuestion();
            NorthBridge.setText(String.valueOf(State.QUESTION));
        } else if (myMaze.getMyCurrentRoom().getMyBridgeN().getOpenStatus()){
            myMaze.moveLocation(Direction.NORTH);
            setBridges();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }
    @FXML
    private void EastClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeE().getQuestionStatus()) {
            generateQuestion();
            EastBridge.setText(String.valueOf(State.QUESTION));
        } else if (myMaze.getMyCurrentRoom().getMyBridgeE().getOpenStatus()){
            myMaze.moveLocation(Direction.EAST);
            setBridges();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }
    @FXML
    private void WestClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeW().getQuestionStatus()) {
            generateQuestion();
            WestBridge.setText(String.valueOf(State.QUESTION));
        } else if (myMaze.getMyCurrentRoom().getMyBridgeW().getOpenStatus()){
            myMaze.moveLocation(Direction.WEST);
            setBridges();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }
    @FXML
    private void SouthClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeS().getQuestionStatus()) {
            generateQuestion();
            SouthBridge.setText(String.valueOf(State.QUESTION));
        } else if (myMaze.getMyCurrentRoom().getMyBridgeS().getOpenStatus()){
            myMaze.moveLocation(Direction.SOUTH);
            setBridges();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }

    @FXML
    private void generateQuestion() {
        pauseBridges(true);
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

    /**
     * Checks if the given answer to the question is correct.
     * @param theGuess the answer to the question
     */
    private void checkAnswer(final String theGuess) {
        if (theGuess.equals(myAnswer)) {
            resultLabel.setText("Correct!\nYou may continue.");
            unlockBridge();
        } else {
            resultLabel.setText("Incorrect!\nThis bridge has broken.");
            lockBridge();
        }
        pauseBridges(false);
        setAnswerButtonsDisabled(true);
    }

    /**
     * Checks if the given short answer to the question is correct.
     * @param theGuess the short answer to the question
     */
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

    @FXML
    private void cheatCode() {
        if (cheatField.getText().equals("GNQ")) {
            generateQuestion();
        }
        if (cheatField.getText().equals("WWCD")) {
            myMaze.setMyCurrentRoom(3,3);
            setBridges();
        }
    }


}
