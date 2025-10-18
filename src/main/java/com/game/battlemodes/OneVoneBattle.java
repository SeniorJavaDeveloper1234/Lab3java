package com.game.battlemodes;

import com.game.droids.Droid;
import lombok.Getter;
import lombok.Setter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class OneVoneBattle extends Battle {

    @Getter
    private final String logFileName;
    private final String battleStartTime;

    @Getter
    @Setter
    private Droid droid1;
    @Getter
    @Setter
    private Droid droid2;

    public OneVoneBattle() {
        super();
        this.battleStartTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.logFileName = "E:/logsDroid/OneVoneBattle_" + this.battleStartTime + ".log";

        logToFile("===== Бій розпочато о " + this.battleStartTime + " =====");
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
    public void startBattle() {
        battleProcess();
    }
    @Override
    protected void battleProcess() {
        renameDroids();
        int round = 1;
        do {

            logToFile("=========Раунд " + round + "=============");

            Random random = new Random();

            Droid attacker;
            Droid defender;

            boolean droidStarts = random.nextBoolean();

            if (droidStarts) {
                attacker = getDroid1();
                defender = getDroid2();
            } else {
                attacker = getDroid2();
                defender = getDroid1();
            }

            double attack = attacker.attack(random, defender);

            if(defender.getHealth() < 0) {
                defender.setHealth(0);
            }

            String report = String.format("%s атакує %s! Шкода: %.1f. %s HP: %.1f",
                    attacker.getName(), defender.getName(), attack,
                    defender.getName(), defender.getHealth());
            logToFile(report);

            round++;

        } while (!isGameOver());

        logToFile("--------------------------");
        logToFile(getWinnerReport());
    }

    private boolean isGameOver() {
        return getDroid1().getHealth() <= 0 || getDroid2().getHealth() <= 0;
    }

    private String getWinnerReport() {
        if (getDroid1().getHealth() <= 0) {
            return getDroid2().getName() + " перемагає! 🎉";
        } else if (getDroid2().getHealth() <= 0) {
            return getDroid1().getName() + " перемагає! 🎉";
        }
        return "Нічия.";
    }

    private void renameDroids(){
        droid1.setName(droid1.getName() + "1");
        droid2.setName(droid2.getName() + "2");
    }



}
