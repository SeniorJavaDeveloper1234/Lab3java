package com.game.battlemodes;

import com.game.droids.Droid;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class TeamBattle extends Battle {

    @Getter
    private final String logFileName;
    private final String battleStartTime;

    @Getter @Setter
    private List<Droid> teamA;

    @Getter @Setter
    private List<Droid> teamB;



    public TeamBattle() {
        super();
        this.battleStartTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.logFileName = "E:/logsDroid/TeamBattle_" + this.battleStartTime + ".log";

        logToFile("===== Бій розпочато о " + this.battleStartTime + " =====");
    }

    private boolean isTeamDefeated(List<Droid> team) {
        return team.stream().allMatch(d -> d.getHealth() <= 0);
    }

    private boolean isGameOver() {
        return isTeamDefeated(teamA) || isTeamDefeated(teamB);
    }

    private Droid getRandomAliveDroid(List<Droid> team) {
        Random random = new Random();

        List<Droid> aliveDroids = team.stream()
                .filter(d -> d.getHealth() > 0)
                .toList();

        if (aliveDroids.isEmpty()) return null;

        return aliveDroids.get(random.nextInt(aliveDroids.size()));
    }

    @Override
    protected void logToFile(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(logFileName, true))) {
            out.println(message);
        } catch (IOException e) {
            System.err.println("Помилка запису в лог-файл: " + e.getMessage());
        }
    }

    @Override
    protected void battleProcess() {
        renameDroids();
        int round = 1;
        do{

            logToFile("=========Раунд " + round + "=============");
            Random random = new Random();

            List<Droid> attackingTeam;
            List<Droid> defendingTeam;

            if (random.nextBoolean()) {
                attackingTeam = teamA;
                defendingTeam = teamB;
            } else {
                attackingTeam = teamB;
                defendingTeam = teamA;
            }

            Droid attacker = getRandomAliveDroid(attackingTeam);
            Droid defender = getRandomAliveDroid(defendingTeam);

            if (attacker == null || defender == null) continue;

            double defenderHealth = defender.getHealth();
            double attack = attack(attacker.getDamage(), random);
            defender.setHealth(defenderHealth - attack);

            if(defender.getHealth() < 0){
                defender.setHealth(0);
            }

            String report = String.format("Команда %s (Атака) | %s завдає %.1f шкоди %s. %s HP: %.1f",
                    (attackingTeam == teamA ? "A" : "B"), attacker.getName(),
                    attack , defender.getName(), defender.getName(), defender.getHealth());
            logToFile(report);

            round++;

        } while (!isGameOver());

        logToFile("--------------------------");
        logToFile(getWinnerReport());
    }

    @Override
    public void startBattle() {
        battleProcess();
    }


    private String getWinnerReport() {
        if (isTeamDefeated(teamB)) {
            return "Команда A ПЕРЕМАГАЄ! 🎉";
        } else if (isTeamDefeated(teamA)) {
            return "Команда B ПЕРЕМАГАЄ! 🎉";
        }
        return "Нічия.";
    }

    private void renameDroids(){

        int i = 1;
        for (Droid droid : teamA) {
            droid.setName(droid.getName() + i);
            i++;
        }

        int j = 1;
        for (Droid droid : teamB) {
            droid.setName(droid.getName() + j);
            j++;
        }
    }

    @Override
    protected double attack(double maxDamage, Random random){
        return random.nextDouble(5.0, maxDamage);
    }

}