package com.game.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;


public class SceneManager {

    private final Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setFullScreen(true);
    }

    public void switchScene(String fxmlFileName, String title) {
        try {

            String fxmlPath = "/com/game/fxml/" + fxmlFileName;

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Scene scene = primaryStage.getScene();
            if (scene == null) {
                scene = new Scene(root);
                primaryStage.setScene(scene);
            } else {
                scene.setRoot(root);
            }

            primaryStage.setTitle(title);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Помилка завантаження сцени: " + fxmlFileName);
            e.printStackTrace();
        }
    }


}
