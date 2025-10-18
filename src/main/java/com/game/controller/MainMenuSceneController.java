package com.game.controller;

import com.game.Main;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuSceneController implements Initializable {


    // Отримання елементів з FXML
    @FXML private GridPane rootPane; // Кореневий елемент (для прив'язки)
    @FXML private Label titleLabel;
    @FXML private Button startButton;
    @FXML private Button settingsButton;
    @FXML private Button exitButton;

    // Метод викликається після того, як всі FXML-елементи створені

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Потрібно дочекатися, поки rootPane буде вставлений у Scene
        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {

                titleLabel.styleProperty().bind(
                        Bindings.concat(
                                "-fx-font-size: ",
                                newScene.heightProperty().divide(15).asString("%.0f"),
                                "px; -fx-font-weight: bold;")
                );

                String buttonStylePrefix = "-fx-font-size: ";
                String buttonStyleSuffix = "px;";

                var buttonSizeBinding = Bindings.concat(
                        buttonStylePrefix,
                        newScene.heightProperty().divide(35).asString("%.0f"),
                        buttonStyleSuffix);

                startButton.styleProperty().bind(buttonSizeBinding);
                settingsButton.styleProperty().bind(buttonSizeBinding);
                exitButton.styleProperty().bind(buttonSizeBinding);
            }
        });
    }

    @FXML
    public void handleStartGame(ActionEvent event){
        SceneManager sceneManager = Main.getSceneManager();

        sceneManager.switchScene("GameMenu.fxml", "GameMenu");
    }

    @FXML
    public void handleRead(ActionEvent event){
        SceneManager sceneManager = Main.getSceneManager();

        sceneManager.switchScene("LogViewer.fxml", "GameMenu");
    }

    @FXML
    public void handleExt(ActionEvent event){
        Platform.exit();

        //sceneManager.switchScene("GameMenu.fxml", "GameMenu");
    }


}
