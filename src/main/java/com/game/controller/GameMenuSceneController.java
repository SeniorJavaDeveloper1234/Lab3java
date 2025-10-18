package com.game.controller;

import com.game.Main;
import com.game.battlemodes.Battle;
import com.game.battlemodes.OneVoneBattle;
import com.game.battlemodes.TeamBattle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuSceneController implements Initializable {

    @FXML private GridPane rootPane;
    @FXML private Button oneVoneButton;
    @FXML private Button teamButton;
    @FXML private Button exitToMainnMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {



                String buttonStylePrefix = "-fx-font-size: ";
                String buttonStyleSuffix = "px;";

                var buttonSizeBinding = Bindings.concat(
                        buttonStylePrefix,
                        newScene.heightProperty().divide(35).asString("%.0f"),
                        buttonStyleSuffix);

                oneVoneButton.styleProperty().bind(buttonSizeBinding);
                teamButton.styleProperty().bind(buttonSizeBinding);
                exitToMainnMenuButton.styleProperty().bind(buttonSizeBinding);
            }
        });
    }

    @FXML
    private void handleExitToMainnMenuButton(ActionEvent event) {
        SceneManager sceneManager = Main.getSceneManager();



        sceneManager.switchScene("MainMenu.fxml","Droid Battle");
    }

    @FXML
    private void handleOneVoneMenuButton(ActionEvent event) {
        SceneManager sceneManager = Main.getSceneManager();

        Battle battle = new OneVoneBattle();
        Main.setBattle(battle);

        sceneManager.switchScene("OneVoneMenu.fxml","1v1 menu");
    }

    @FXML
    private void handleTeamMenuButton(ActionEvent event) {
        SceneManager sceneManager = Main.getSceneManager();

        Battle battle = new TeamBattle();
        Main.setBattle(battle);

        sceneManager.switchScene("TeamMenu.fxml","1v1 menu");
    }


}
