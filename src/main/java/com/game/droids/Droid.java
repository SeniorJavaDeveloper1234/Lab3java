package com.game.droids;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public abstract class Droid {
    protected String name;
    protected double health;
    protected double damage;
}
