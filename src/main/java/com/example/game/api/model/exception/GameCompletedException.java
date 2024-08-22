package com.example.game.api.model.exception;

public class GameCompletedException extends RuntimeException {
    public GameCompletedException() {
        super ("Game already completed, please create a new game.");
    }
}
