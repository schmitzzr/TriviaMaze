package com.triviamaze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the Help Scene, which displays the gameplay instructions.
 */
public class HelpSceneController {

    private static final GameSceneController myGameSceneController = new GameSceneController();
    private static final MainMenuController myMainMenu = new MainMenuController();

    /**
     * Returns to main menu.
     * @param myEvent returning to main menu
     * @throws IOException in case files are not found
     */
    @FXML
    public void returnToMainMenu(final ActionEvent myEvent) throws IOException {
        myGameSceneController.returnToMainMenu(myEvent);
    }

    /**
     * Returns to the autosaved game.
     * @param myEvent returning to the game
     * @throws IOException in case files are not found
     */
    @FXML
    public void returnToGame(final ActionEvent myEvent) throws IOException {
        myMainMenu.loadGame(myEvent);
    }

}
