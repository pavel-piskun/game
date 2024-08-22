package com.example.game.service;

import com.example.game.api.model.Figure;
import com.example.game.api.model.MoveResult;
import com.example.game.api.model.WhoWin;
import com.example.game.repository.GameRepository;
import com.example.game.repository.entity.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GameServiceTest {
    @Autowired
    GameService gameService;
    @MockBean
    GameRepository gameRepository;
    @MockBean
    RobotService robotService;


    @Test
    void testMakeMove() {
        Mockito.when(robotService.makeMove()).thenReturn(Figure.PAPER);
        Mockito.when(gameRepository.findById(Mockito.any())).thenReturn(Optional.of(new Game()));
        MoveResult result = gameService.makeMove(1L, Figure.PAPER);
        assertNotNull(result);
        assertEquals(WhoWin.TIE, result.getWhoWin());
        result = gameService.makeMove(1L, Figure.SCISSORS);
        assertNotNull(result);
        assertEquals(WhoWin.HUMAN, result.getWhoWin());
        result = gameService.makeMove(1L, Figure.ROCK);
        assertNotNull(result);
        assertEquals(WhoWin.ROBOT, result.getWhoWin());
    }
}
