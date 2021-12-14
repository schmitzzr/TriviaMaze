package com.triviamaze;

import com.triviamaze.maze.Maze;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller for the Settings Scene, which includes sound sliders and clearing the scoreboard.
 */
public class SettingsSceneController implements Initializable {

    @FXML
    private Label musicPercentLabel;

    @FXML
    private Label effectsPercentLabel;

    @FXML
    private Slider musicSlider;

    @FXML
    private Slider effectsSlider;

    private int myMusicVolume;
    private int myEffectsVolume;

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

    @FXML
    private void returnToTheGame(final ActionEvent myEvent) throws IOException {
        MainMenuController myMainMenu = new MainMenuController();
        myMainMenu.loadGame(myEvent);
    }

    /**
     * Initializes sound
     * @param theUrl address to the sound file.
     * @param resourceBundle for localization.
     */
    @Override
    public void initialize(URL theUrl, ResourceBundle resourceBundle) {

        musicSlider.setValue(myMusicVolume);
        myMusicVolume = (int) musicSlider.getValue();
        musicPercentLabel.setText(myMusicVolume + "%");
        myEffectsVolume = (int) effectsSlider.getValue();
        effectsPercentLabel.setText(myEffectsVolume + "%");

        musicSlider.valueProperty().addListener((observableValue, number, t1) -> {
            myMusicVolume = (int) musicSlider.getValue();

            musicPercentLabel.setText(myMusicVolume + "%");
        });

        effectsSlider.valueProperty().addListener((observableValue, number, t1) -> {
            myEffectsVolume = (int) effectsSlider.getValue();
            effectsPercentLabel.setText(myEffectsVolume + "%");
        });
    }

    MediaPlayer mediaPlayer;
    public void music(){
        Media sound = new Media(Objects.requireNonNull(getClass().getResource("sounds/sound.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.3);
    }

    /**
     * Getter for the music volume
     * @return the music volume
     */
    public int getMyMusicVolume() {
        return myMusicVolume;
    }

    /**
     * Getter for the effects volume
     * @return the effects volume
     */
    public int getMyEffectsVolume() {
        return myEffectsVolume;
    }


}
