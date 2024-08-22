package com.example.game.api.model;

import java.util.Random;

public enum Figure {
    PAPER, ROCK, SCISSORS;

    private static final Random RNDM = new Random();

    public static Figure randomFigure()  {
        Figure[] directions = values();
        return directions[RNDM.nextInt(directions.length)];
    }
}
