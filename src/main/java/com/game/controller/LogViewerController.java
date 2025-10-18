package com.game.controller;

import com.game.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogViewerController {

    @FXML private TextArea logTextArea;

    private Stage stage;

    @FXML
    private void handleChooseFile(ActionEvent event) {


        if (stage == null) {
            stage = (Stage) logTextArea.getScene().getWindow();
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Оберіть Файл Логу (OneVoneBattle_...)");

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            readAndDisplayFile(file.toPath());
        }
    }


    private void readAndDisplayFile(Path filePath) {
        try {
            String content = Files.readString(filePath);

            logTextArea.setText(content);
            logTextArea.setScrollTop(Double.MAX_VALUE);

        } catch (IOException e) {
            String errorMsg = "Помилка читання: Не вдалося прочитати файл " + filePath.getFileName() + ".";
            logTextArea.setText(errorMsg);
            System.err.println(errorMsg);
        }
    }

    @FXML
    private void handleReturnToMenu(ActionEvent event) {
        Main.getSceneManager().switchScene("MainMenu.fxml", "Droid Battle");
    }
}