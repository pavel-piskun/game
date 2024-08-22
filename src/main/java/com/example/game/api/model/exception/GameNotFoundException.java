package com.example.game.api.model.exception;
public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super ("Game doesn't exists, please create a new game.");
    }
}
