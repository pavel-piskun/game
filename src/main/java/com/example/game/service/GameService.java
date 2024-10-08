package com.example.game.service;

import com.example.game.api.model.MoveResult;

import java.util.List;

public interface GameService {
    Long createGame();
    MoveResult makeMove(Long gameId, String figure);

    void completeGame(Long gameId);

    List<MoveResult> getStatistics(Long gameId);
}
