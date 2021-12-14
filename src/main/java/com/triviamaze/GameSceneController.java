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
    private TextField typeAnswerTextField, cheatField;
    @FXML
    private Label winLabel, loseLabel, locationLabel;
    @FXML
    private Button EastBridge = new Button(),
            NorthBridge = new Button(),
            WestBridge = new Button(),
            SouthBridge = new Button();

    /** Initializes the trivia database to be used */
    private final Trivia myTrivia = new Trivia("jdbc:sqlite:questions.db", "multipleChoice");

    /** Initializes the maze to be used by default */
    private Maze myMaze = new Maze(4,4,0,0,3,3);

    /** The correct answer to the question */
    private String myAnswer;

    private Stage myStage;
    private Scene myScene;
    private Parent myRoot;

    /**
     * Sets the maze to be used.
     * @param theMaze the maze to be used
     */
    @FXML
    public void setMyMaze(final Maze theMaze) {
        myMaze = theMaze;
        System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        setTheRoom();
    }

    @FXML
    private void muteButtonClicked(final ActionEvent event) {
    }




    /**
     * Initializes the bridges for the room.
     * If the player has run out of bridges or has reached the end, a message pops up indicating so.
     */
    @FXML
    public void setTheRoom() {
        setLocationLabel();

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
            pauseBridges(true);
            winLabel.setVisible(true);
        } else if (!myMaze.checkAbilityToContinue()) {
            System.out.println("You lost! :(");
            loseLabel.setVisible(true);
        }
    }

    /**
     * Locks or unlocks a bridge if the question is answered incorrectly or correctly, respectively.
     * @param theStatus true to unlock the bridge, false to break it
     */
    @FXML
    public void unlockTheBridge(boolean theStatus) {
        if (myMaze.getMyCurrentRoom().getMyBridgeN().getQuestionStatus()) {
            myMaze.getMyCurrentRoom().setBridgeQStatus(Direction.NORTH, false);
            myMaze.breakOrSolveBridge(Direction.NORTH, theStatus);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeE().getQuestionStatus()) {
            myMaze.getMyCurrentRoom().setBridgeQStatus(Direction.EAST, false);
            myMaze.breakOrSolveBridge(Direction.EAST, theStatus);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeW().getQuestionStatus()) {
            myMaze.getMyCurrentRoom().setBridgeQStatus(Direction.WEST, false);
            myMaze.breakOrSolveBridge(Direction.WEST, theStatus);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeS().getQuestionStatus()) {
            myMaze.getMyCurrentRoom().setBridgeQStatus(Direction.SOUTH, false);
            myMaze.breakOrSolveBridge(Direction.SOUTH, theStatus);
        }
        setTheRoom();
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

    /**
     * Exits the program.
     * @param event exit the program
     */
    @FXML
    private void exitButtonClicked(final ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    /**
     * Returns to main menu by switching scene.
     * @param event returning to the main menu
     * @throws IOException in case the fxml file is not found
     */
    @FXML
    private void returnToMainMenu(final ActionEvent event) throws IOException {
        myRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        myStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/Styles.css")).toExternalForm());
        myScene.setFill(Color.TRANSPARENT);
        myStage.setScene(myScene);
        myStage.show();
    }

    @FXML
    private void helpClicked(final ActionEvent event) throws IOException {
        myRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HelpScene.fxml")));
        myStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        myScene = new Scene(myRoot);
        myScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/HelpScene.css")).toExternalForm());
        myScene.setFill(Color.TRANSPARENT);
        myStage.setScene(myScene);
        myStage.show();
    }

    /**
     * Action assigned to clicking north bridge.
     * Either asks a question or moves the player based on bridge status.
     */
    @FXML
    private void NorthClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeN().getSolvedStatus()) {
            generateQuestion();
            myMaze.getMyCurrentRoom().getMyBridgeN().setQuestionStatus(true);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeN().getOpenStatus()){
            myMaze.moveLocation(Direction.NORTH);
            setTheRoom();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }

    /**
     * Action assigned to clicking east bridge.
     * Either asks a question or moves the player based on bridge status.
     */
    @FXML
    private void EastClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeE().getSolvedStatus()) {
            generateQuestion();
            myMaze.getMyCurrentRoom().getMyBridgeE().setQuestionStatus(true);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeE().getOpenStatus()){
            myMaze.moveLocation(Direction.EAST);
            setTheRoom();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }

    /**
     * Action assigned to clicking west bridge.
     * Either asks a question or moves the player based on bridge status.
     */
    @FXML
    private void WestClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeW().getSolvedStatus()) {
            generateQuestion();
            myMaze.getMyCurrentRoom().getMyBridgeW().setQuestionStatus(true);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeW().getOpenStatus()){
            myMaze.moveLocation(Direction.WEST);
            setTheRoom();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }

    /**
     * Action assigned to clicking south bridge.
     * Either asks a question or moves the player based on bridge status.
     */
    @FXML
    private void SouthClick() {
        if (!myMaze.getMyCurrentRoom().getMyBridgeS().getSolvedStatus()) {
            generateQuestion();
            myMaze.getMyCurrentRoom().getMyBridgeS().setQuestionStatus(true);
        } else if (myMaze.getMyCurrentRoom().getMyBridgeS().getOpenStatus()){
            myMaze.moveLocation(Direction.SOUTH);
            setTheRoom();
            System.out.println("Current room: " + myMaze.getMyCurrentRoom());
        }
    }

    /**
     * Generates a question, filling in the trivia pane.
     */
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

    /**
     * Checks if option A was the correct answer.
     */
    @FXML
    private void answerA() {
        checkAnswer("A");
    }

    /**
     * Checks if option B was the correct answer.
     */
    @FXML
    private void answerB() {
        checkAnswer("B");
    }

    /**
     * Checks if option C was the correct answer.
     */
    @FXML
    private void answerC() {
        checkAnswer("C");
    }

    /**
     * Checks if option D was the correct answer.
     */
    @FXML
    private void answerD() {
        checkAnswer("D");
    }

    /**
     * Gets inputted answer from text field and checks if the answer is correct.
     */
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
        if (myTrivia.getResult(theGuess)) {
            resultLabel.setText("Correct!\nYou may continue.");
            unlockTheBridge(true);
        } else {
            resultLabel.setText("Incorrect!\nThis bridge has broken.");
            unlockTheBridge(false);
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

    /**
     * Sets multiple choice answer buttons disabled or enabled
     * @param theState true to disable buttons, false to enable them
     */
    @FXML
    private void setAnswerButtonsDisabled(final boolean theState) {
        buttonA.setDisable(theState);
        buttonB.setDisable(theState);
        buttonC.setDisable(theState);
        buttonD.setDisable(theState);
    }

    /**
     * Cheat codes, mostly for testing purposes.
     */
    @FXML
    private void cheatCode() {
        String cheat = cheatField.getText().toUpperCase();
        switch (cheat) {
            case "NUQU" -> generateQuestion();
            case "WWCD" -> {
                myMaze.setMyCurrentRoom(3, 3);
                setTheRoom();
            }
            case "GTQR" -> checkAnswer(myAnswer);
            case "WHEREAMI" -> locationLabel.setVisible(true);
            default -> {
                giveUp();
                System.out.println("Try not cheating next time!");
            }
        }
        cheatField.setText("");
    }

    @FXML
    private void giveUp() {
        myMaze.getMyCurrentRoom().setBridgeStatus(Direction.NORTH, false);
        myMaze.getMyCurrentRoom().setBridgeStatus(Direction.SOUTH, false);
        myMaze.getMyCurrentRoom().setBridgeStatus(Direction.EAST, false);
        myMaze.getMyCurrentRoom().setBridgeStatus(Direction.WEST, false);
        setTheRoom();
    }

    @FXML
    private void setLocationLabel() {
        int row = myMaze.getMyCurrentRoom().getMyRow();
        int column = myMaze.getMyCurrentRoom().getMyColumn();
        locationLabel.setText("Row " + row + ", Column " + column);
    }

}
