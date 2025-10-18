package com.game.droids;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class NinjaDroid extends Droid {
    public NinjaDroid(String name, double health, double maxDamage) {
        super(name, health, maxDamage);
    }

    @Override
    public double attack(Random random, Droid target) {

        double attackDamage;

        if(this.health <= 10) {
            attackDamage = random.nextDouble(3.0, maxDamage * 3);
        }
        else{
            attackDamage = random.nextDouble(8.0, maxDamage);
        }

        target.setHealth(target.getHealth() - attackDamage);

        return attackDamage;

    }

}
