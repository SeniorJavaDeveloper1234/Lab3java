package com.game.controller;

import com.game.Main;
import com.game.battlemodes.OneVoneBattle;
import com.game.droids.BoxerDroid;
import com.game.droids.Droid;
import com.game.droids.DuelantDroid;
import com.game.droids.NinjaDroid;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OneVoneMenuController implements Initializable {

    @FXML private GridPane rootPane;
    @FXML private Button startButton;
    @FXML private ChoiceBox<String> choiceBox1;
    @FXML private ChoiceBox<String> choiceBox2;

    private Droid droid1;
    private Droid droid2;

    private final ObservableList<String> droidTypes = FXCollections.observableArrayList(
            "Boxer",
            "Duelant",
            "Ninja"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBox1.setItems(droidTypes);
        choiceBox2.setItems(droidTypes);

        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {



                String buttonStylePrefix = "-fx-font-size: ";
                String buttonStyleSuffix = "px;";

                var buttonSizeBinding = Bindings.concat(
                        buttonStylePrefix,
                        newScene.heightProperty().divide(35).asString("%.0f"),
                        buttonStyleSuffix);

                startButton.styleProperty().bind(buttonSizeBinding);

            }
        });
    }

    @FXML
    public void handleStartButtonAction(ActionEvent actionEvent) {
        if(choiceBox1.getValue() != null && choiceBox2.getValue() != null) {

            String choise1 = choiceBox1.getValue();
            String choise2 = choiceBox2.getValue();

            switch (choise1) {
                case "Boxer" -> droid1 = new BoxerDroid("Boxer",100, 10.5);
                case "Duelant" -> droid1 = new DuelantDroid("Duelant",80, 12.5);
                case "Ninja" -> droid1 = new NinjaDroid("Ninja",90, 11.5);
            }

            switch (choise2) {
                case "Boxer" -> droid2 = new BoxerDroid("Boxer",100, 10.5);
                case "Duelant" -> droid2 = new DuelantDroid("Duelant",80, 12.5);
                case "Ninja" -> droid2 = new NinjaDroid("Ninja",90, 11.5);
            }

            OneVoneBattle battle = (OneVoneBattle) Main.getBattle();
            battle.setDroid1(droid1);
            battle.setDroid2(droid2);

            battle.startBattle();

            Main.getSceneManager().switchScene("BattleReport.fxml", "Звіт 1v1");

        }

    }


}
