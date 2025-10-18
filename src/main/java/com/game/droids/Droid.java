package com.game.droids;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@AllArgsConstructor
public abstract class Droid {
    protected String name;
    protected double health;
    protected double maxDamage;

    public abstract double attack(Random random, Droid target);
}
