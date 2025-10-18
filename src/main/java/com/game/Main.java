package com.game;

import com.game.battlemodes.Battle;
import com.game.controller.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

public class Main extends Application {

    @Getter
    private static SceneManager sceneManager;

    @Getter
    @Setter
    private static Battle battle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneManager = new SceneManager(primaryStage);
        sceneManager.switchScene("MainMenu.fxml", "Main Menu");
    }



}
