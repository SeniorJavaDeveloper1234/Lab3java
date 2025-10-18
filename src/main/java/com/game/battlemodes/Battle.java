package com.game.battlemodes;

import java.util.Random;

public abstract class Battle {

    protected abstract void logToFile(String message);
    protected abstract void battleProcess();
    public abstract void startBattle();


}
