package com.game.droids;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BoxerDroid extends Droid {
    public BoxerDroid(String name, double health, double maxDamage) {
        super(name, health, maxDamage);
    }

    @Override
    public double attack(Random random, Droid target) {

        double attackDamage;

        if(this.health <= 10) {
            attackDamage = random.nextDouble(10.0, maxDamage * 2);
        }
        else{
            attackDamage = random.nextDouble(5.0, maxDamage);
        }

        target.setHealth(target.getHealth() - attackDamage);

        return attackDamage;

    }

}
