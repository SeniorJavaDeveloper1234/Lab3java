package com.game.controller;

import com.game.Main;
import com.game.battlemodes.OneVoneBattle;
import com.game.battlemodes.TeamBattle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class BattleReportController implements Initializable {

    @FXML private TextArea reportTextArea;
    private String logFilePath;

    @FXML
    private void handleShowReport(ActionEvent event) {
        if (logFilePath == null) {
            reportTextArea.setText("Помилка: Не знайдено імені лог-файлу.");
            return;
        }

        try {
            String content = Files.readString(Paths.get(logFilePath));
            reportTextArea.setText(content);
            reportTextArea.setScrollTop(Double.MAX_VALUE);

        } catch (IOException e) {
            reportTextArea.setText("Помилка: Не вдалося прочитати лог-файл: " + logFilePath);
            System.err.println("Помилка читання лог-файлу: " + e.getMessage());
        }
    }

    @FXML
    private void handleReturnToMenu(ActionEvent event) {
        Main.getSceneManager().switchScene("MainMenu.fxml", "Droid Battle");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.getBattle() instanceof OneVoneBattle){
            OneVoneBattle oneVoneBattle = (OneVoneBattle) Main.getBattle();
            logFilePath = oneVoneBattle.getLogFileName();
        }
        else {
            TeamBattle teamBattle = (TeamBattle) Main.getBattle();
            logFilePath = teamBattle.getLogFileName();
        }

    }
}