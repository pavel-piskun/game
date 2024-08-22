package com.example.game.api.model.exception;

import com.example.game.api.model.Figure;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnknownFigureException extends RuntimeException{
    public UnknownFigureException() {
        super("Possible values: " + Stream.of(Figure.values()).map(Enum::name).collect(Collectors.joining(", ")));
    }
}
