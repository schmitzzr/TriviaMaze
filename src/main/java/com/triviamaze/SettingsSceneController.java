package com.triviamaze;

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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SettingsSceneController implements Initializable {

    @FXML
    private Label musicPercentLabel;

    @FXML
    private Label effectsPercentLabel;

    @FXML
    private Slider musicSlider;

    @FXML
    private Slider effectsSlider;

    private int musicVolume;
    private int effectsVolume;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        musicVolume = (int) musicSlider.getValue();
        musicPercentLabel.setText(musicVolume + "%");

        effectsVolume = (int) effectsSlider.getValue();
        effectsPercentLabel.setText(effectsVolume + "%");

        musicSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                musicVolume = (int) musicSlider.getValue();
                musicPercentLabel.setText(musicVolume + "%");
            }
        });

        effectsSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                effectsVolume = (int) effectsSlider.getValue();
                effectsPercentLabel.setText(effectsVolume + "%");
            }
        });
    }

    public int getMusicVolume() {
        return musicVolume;
    }

    public int getEffectsVolume() {
        return effectsVolume;
    }


}
