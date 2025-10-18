package com.game.droids;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class DuelantDroid extends Droid {
    public DuelantDroid(String name, double health, double maxDamage) {
        super(name, health, maxDamage);
    }

    @Override
    public double attack(Random random, Droid target) {

        double attackDamage;

        if(this.health <= 30) {
            attackDamage = random.nextDouble(7.0, maxDamage * 1.5);
        }
        else{
            attackDamage = random.nextDouble(10.0, maxDamage);
        }

        target.setHealth(target.getHealth() - attackDamage);

        return attackDamage;

    }

}
