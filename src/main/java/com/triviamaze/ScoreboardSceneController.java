package com.triviamaze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ScoreboardSceneController {

    private static final GameSceneController myGameSceneController = new GameSceneController();

    /**
     * Returns to the main menu
     * @param theEvent clicking "return to main menu" button
     * @throws IOException in case files are not found
     */
    @FXML
    private void returnToMainMenu(ActionEvent theEvent) throws IOException {
        myGameSceneController.returnToMainMenu(theEvent);
    }
}
