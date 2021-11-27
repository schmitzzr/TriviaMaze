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

public class HelpSceneController {

    @FXML
    public void returnToMainMenu(final ActionEvent event) throws IOException {
        Parent myRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        Stage myStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene myScene = new Scene(myRoot);
        myScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/triviamaze/Styles.css")).toExternalForm());
        myScene.setFill(Color.TRANSPARENT);
        myStage.setScene(myScene);
        myStage.show();
    }

}
