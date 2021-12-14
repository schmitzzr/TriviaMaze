package com.triviamaze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ScoreboardSceneController {

    private static final GameSceneController myGameSceneController = new GameSceneController();

    @FXML
    private void returnToMainMenu(ActionEvent theEvent) throws IOException {
        myGameSceneController.returnToMainMenu(theEvent);
    }
}
