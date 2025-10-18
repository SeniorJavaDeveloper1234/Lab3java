package com.game.controller;

import com.game.Main;
import com.game.battlemodes.TeamBattle; // ⬅️ Новий клас
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TeamMenuController implements Initializable {

    @FXML private GridPane rootPane;
    @FXML private Button startButton;

    @FXML private ChoiceBox<String> teamA_choice1;
    @FXML private ChoiceBox<String> teamA_choice2;
    @FXML private ChoiceBox<String> teamA_choice3;
    @FXML private ChoiceBox<String> teamB_choice1;
    @FXML private ChoiceBox<String> teamB_choice2;
    @FXML private ChoiceBox<String> teamB_choice3;

    private final ObservableList<String> droidTypes = FXCollections.observableArrayList(
            "Boxer", "Duelant", "Ninja"
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List.of(teamA_choice1, teamA_choice2, teamA_choice3,
                        teamB_choice1, teamB_choice2, teamB_choice3)
                .forEach(cb -> cb.setItems(droidTypes));

        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                String buttonStylePrefix = "-fx-font-size: ";
                String buttonStyleSuffix = "px;";
                var buttonSizeBinding = Bindings.concat(
                        buttonStylePrefix, newScene.heightProperty().divide(50).asString("%.0f"), buttonStyleSuffix);
                startButton.styleProperty().bind(buttonSizeBinding);
            }
        });

        teamA_choice1.setValue("Boxer");
        teamB_choice1.setValue("Duelant");
    }

    @FXML
    public void handleStartButtonAction(ActionEvent actionEvent) {

        SceneManager sceneManager = Main.getSceneManager();

        List<String> teamA_choices = List.of(teamA_choice1.getValue(), teamA_choice2.getValue(), teamA_choice3.getValue());
        List<String> teamB_choices = List.of(teamB_choice1.getValue(), teamB_choice2.getValue(), teamB_choice3.getValue());

        List<Droid> teamA = createTeam(teamA_choices, "A");
        List<Droid> teamB = createTeam(teamB_choices, "B");

        TeamBattle battle = (TeamBattle) Main.getBattle();
        battle.setTeamA(teamA);
        battle.setTeamB(teamB);

        battle.startBattle();
        sceneManager.switchScene("BattleReport.fxml", "Звіт Командного Бою");
    }

    private List<Droid> createTeam(List<String> choices, String teamName) {
        return choices.stream().map(type -> {
            Droid droid;
            switch (type) {
                case "Boxer" -> droid = new BoxerDroid("Boxer (" + teamName + ")", 100, 10.5);
                case "Duelant" -> droid = new DuelantDroid("Duelant (" + teamName + ")", 80, 12.5);
                case "Ninja" -> droid = new NinjaDroid("Ninja (" + teamName + ")", 90, 11.5);
                default -> throw new IllegalArgumentException("Невідомий тип");
            }
            return droid;
        }).toList();
    }
}